package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.Order.BillRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Order.OrderMenuRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Order.OrderRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Order.OrderServiceRequestDTO;
import com.ou.restaurantmanagement.Pojos.*;
import com.ou.restaurantmanagement.Repository.Client.OrderClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class OrderClientRepositoryImpl implements OrderClientRepository {
    @Autowired
    private EntityManager _em;

    @Override
    public boolean checkLobby(IBaseRequest input) {
        OrderRequestDTO req = (OrderRequestDTO) input;
        List<Order> order = _em.createQuery("SELECT o FROM Order o, Coefficient c " +
                        "WHERE c.id = o.coef.id AND o.ordBookingDate = :date AND " +
                        "c.coefTypeLesson = :lesson AND o.lob.id = :lob_id", Order.class)
                .setParameter("date", req.getOrd_booking_date())
                .setParameter("lesson", req.getOrd_booking_lesson())
                .setParameter("lob_id", req.getLob_id())
                .getResultList();
        if(order.size() == 0)
            return true;
        return false;
    }

    @Override
    public boolean addOrder(IBaseRequest input) {
        try {
            OrderRequestDTO req = (OrderRequestDTO) input;
            // add order
            Order newOrder = new Order();
            newOrder.setOrdCreatedDate(LocalDate.now());
            newOrder.setOrdBookingDate(req.getOrd_booking_date());
            newOrder.setOrdBookingLesson(req.getOrd_booking_lesson());
            User user = new User();
            user.setId(req.getUser_id());
            newOrder.setUser(user);
            Coefficient coefficient = new Coefficient();
            coefficient.setId(getCoefficientID(req.getOrd_booking_date(), req.getOrd_booking_lesson()));
            newOrder.setCoef(coefficient);
            Lobby lobby = new Lobby();
            lobby.setId(req.getLob_id());
            newOrder.setLob(lobby);
            newOrder.setOrdIsPayment(req.isOrd_is_payment());
            _em.persist(newOrder);

            int order_id = getLastestOrderID(req.getUser_id());

            if(req.isOrd_is_payment()) {
                addBill(new BillRequestDTO(req.getUser_id(), order_id));
            }

            // add order_service
            addOrderService(req.getListService(),order_id);

            // add order_menu
            addOrderMenu(req.getListMenu(), order_id);
            return true;
        } catch (Exception e) {
            System.err.println("ERROR in addOrder(): " + e);
            return false;
        }
    }

    @Override
    public boolean addBill(IBaseRequest input) {
        try{
            // add bill
            BillRequestDTO req = (BillRequestDTO) input;
            Bill bill = new Bill();
            bill.setId(req.getOrderID());
            bill.setBillCreatedDate(LocalDate.now());
            bill.setUser_id(req.getUserID());
            bill.setBillTotalMoney(totalMoney(req.getOrderID()));
            bill.setBillNote(noteBill(req));
            _em.persist(bill);

            // update order
            Order order = getOrderByID(req.getOrderID());
            order.setOrdIsPayment(true);
            _em.merge(order);

            return true;
        } catch (Exception e){
            System.err.println("ERROR in addBill(): " + e);
            return false;
        }
    }

    private int getCoefficientID(LocalDate booking_date, String lesson){
        try {
            DayOfWeek day = booking_date.getDayOfWeek();
            String typeDate;
            if(day.name().compareTo("SUNDAY") == 0)
                typeDate = "cuối tuần";
            else
                typeDate = "ngày thường";

            int id =  _em.createQuery("SELECT c.id FROM Coefficient c " +
                            "WHERE c.coefTypeDate =:typeDate AND c.coefTypeLesson = :lesson", Integer.class)
                    .setParameter("typeDate", typeDate)
                    .setParameter("lesson", lesson)
                    .getSingleResult();
            return id;
        } catch (Exception e){
            System.err.println("ERROR in getCoefficientID(): " + e);
            return 0;
        }
    }

    private void addOrderService(List<OrderServiceRequestDTO> listService, int order_id){
        try {
            Order order = new Order();
            order.setId(order_id);
            listService.forEach(s->{
                OrderService os = new OrderService();
                os.setOrd(order);
                Service service = new Service();
                service.setId(s.getSerID());
                os.setSer(service);
                _em.persist(os);
            });
        } catch (Exception e){
            System.err.println("ERROR in addOrderService(): " + e);
        }
    }

    private void addOrderMenu(List<OrderMenuRequestDTO> listMenu, int order_id){
        try {
            Order order = new Order();
            order.setId(order_id);
            listMenu.forEach(s->{
                OrderMenu om = new OrderMenu();
                om.setOrd(order);
                Menu menu = new Menu();
                menu.setId(s.getMenuID());
                om.setMenu(menu);
                om.setAmountTable(s.getAmountTable());
                _em.persist(om);
            });
        } catch (Exception e){
            System.err.println("ERROR in addOrderMenu(): " + e);
        }
    }

    private int getLastestOrderID(int user_id){
        return _em.createQuery("SELECT max (o.id) FROM Order o " +
                "WHERE o.user.id = :id", Integer.class)
                .setParameter("id", user_id)
                .getSingleResult();
    }
    @Override
    public BigDecimal totalMoney(int order_id){
        BigDecimal totalMenu = new BigDecimal(0);
        BigDecimal totalService = new BigDecimal(0);
        BigDecimal lobbyMoney = new BigDecimal(0);
        // service
        List<OrderService> listOrderService = _em.createQuery("SELECT o FROM OrderService o " +
                        "WHERE o.ord.id = :order_id", OrderService.class)
                .setParameter("order_id", order_id)
                .getResultList();
        for (OrderService o : listOrderService){
            BigDecimal money = _em.createQuery("SELECT s.serPrice FROM Service s " +
                    "WHERE s.id = :id", BigDecimal.class)
                    .setParameter("id", o.getSer().getId())
                    .getSingleResult();
            totalService = totalService.add(money);
        }

        // menu
        List<OrderMenu> listMenu = _em.createQuery("SELECT o FROM OrderMenu o " +
                "WHERE o.ord.id = :order_id", OrderMenu.class)
                .setParameter("order_id", order_id)
                .getResultList();
        for (OrderMenu m : listMenu){
            BigDecimal money = _em.createQuery("SELECT m.menuPrice FROM Menu m " +
                    "WHERE m.id = :id", BigDecimal.class)
                    .setParameter("id", m.getMenu().getId())
                    .getSingleResult();
            money = money.multiply(new BigDecimal(m.getAmountTable()));
            totalMenu = totalMenu.add(money);
        }

        // lobby
        lobbyMoney = _em.createQuery("SELECT l.lobPrice FROM Order o, Lobby l " +
                "WHERE o.lob.id = l.id AND o.id = :order_id", BigDecimal.class)
                .setParameter("order_id", order_id)
                .getSingleResult();

        return totalService.add(totalMenu).add(lobbyMoney);
    }

    private String noteBill(BillRequestDTO req){
        String note = "";
        // service
        List<OrderService> listOrderService = _em.createQuery("SELECT o FROM OrderService o " +
                        "WHERE o.ord.id = :order_id", OrderService.class)
                .setParameter("order_id", req.getOrderID())
                .getResultList();

        for (int i = 0; i < listOrderService.size(); i++){
            note += "SERVICE" + (i+1) + ": ";
            Object[] service = _em.createQuery("SELECT s.id, s.serPrice FROM Service s " +
                            "WHERE s.id = :id", Object[].class)
                    .setParameter("id", listOrderService.get(i).getSer().getId())
                    .getSingleResult();
            note += "service_id(" + service[0] + "), service_price(" + service[1] + "); ";
        }
        // menu
        List<OrderMenu> listMenu = _em.createQuery("SELECT o FROM OrderMenu o " +
                        "WHERE o.ord.id = :order_id", OrderMenu.class)
                .setParameter("order_id", req.getOrderID())
                .getResultList();
        for (int i = 0; i < listMenu.size(); i++){
            Object[] menu = _em.createQuery("SELECT m.id, m.menuPrice FROM Menu m " +
                            "WHERE m.id = :id", Object[].class)
                    .setParameter("id", listMenu.get(i).getMenu().getId())
                    .getSingleResult();
            note += "MENU" + (i+1) + ": menu_id(" + menu[0] + "), menu_price(" + menu[1] + "), amount_table(" +
                    listMenu.get(i).getAmountTable() + "); ";
        }

        // lobby
        Object[] lobby = _em.createQuery("SELECT l.id, l.lobPrice FROM Order o, Lobby l WHERE o.lob.id = l.id " +
                "AND o.id = :order_id", Object[].class)
                .setParameter("order_id", req.getOrderID())
                .getSingleResult();
        note += "LOBBY: lobby_id(" + lobby[0] + "), lobby_price(" + lobby[1] + "); ";

        // coefficient
        double coef = _em.createQuery("SELECT c.coefValue FROM Order o, Coefficient c WHERE o.coef.id = c.id " +
                        "AND o.id = :order_id", Double.class)
                .setParameter("order_id", req.getOrderID())
                .getSingleResult();
        note += "COEFFICIENT: " + coef + "; ";

        return note;
    }

    @Override
    public Order getOrderByID(int order_id){
        Order order = _em.createQuery("SELECT o FROM Order o WHERE o.id = :id", Order.class)
                .setParameter("id", order_id)
                .getSingleResult();
        return order;
    }
}
