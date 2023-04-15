package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.Order.OrderMenuRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Order.OrderRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Order.OrderServiceRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Order.TotalMoneyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.TotalMoneyResponse;
import com.ou.restaurantmanagement.Pojos.*;
import com.ou.restaurantmanagement.Repository.Client.OrderClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Repository
@Transactional
public class OrderClientRepositoryImpl implements OrderClientRepository {
    @PersistenceContext
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
            newOrder.setOrdIsPayment(false);
            _em.persist(newOrder);

            int order_id = getLastestOrderID(req.getUser_id());

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
    public List<Lobby> getListLobbiesByDate(Date date, String lesson) {
        List<Lobby> lobbies = _em.createQuery("SELECT l FROM Lobby l WHERE l.lobIsActive = true", Lobby.class)
                .getResultList();

        List<Lobby> results = new ArrayList<>();
        lobbies.forEach(s -> {
            if(!checkExistLobby(s.getId(), date, lesson))
                results.add(s);
        });
        return results;
    }

    @Override
    public TotalMoneyResponse calculateTotalAmount(TotalMoneyRequestDTO request) {
        BigDecimal totalMenu = new BigDecimal(0);
        BigDecimal totalService = new BigDecimal(0);

        // hệ số tăng giá
        int coefficientID = getCoefficientID(request.getBookingDate(), request.getLesson());
        Coefficient coefficient = _em.createQuery("SELECT c FROM Coefficient c WHERE c.id = :id", Coefficient.class)
                .setParameter("id", coefficientID)
                .getSingleResult();
        String servicesFees = (int) (coefficient.getCoefValue() * 100) - 100 + " %";

        // service
        for(int s : request.getListServices()) {
            BigDecimal money= _em.createQuery("SELECT s.serPrice FROM Service s WHERE s.id =: id", BigDecimal.class)
                    .setParameter("id", s)
                    .getSingleResult();
            totalService = totalService.add(money);
        }

        // menu
        for(OrderMenuRequestDTO s : request.getListMenus()) {
            BigDecimal money= _em.createQuery("SELECT m.menuPrice FROM Menu m WHERE m.id =: id", BigDecimal.class)
                    .setParameter("id", s.getMenuID())
                    .getSingleResult();
            money = money.multiply(new BigDecimal(s.getAmountTable()));
            totalMenu = totalMenu.add(money);
        }

        // totla money
        BigDecimal total = totalService.add(totalMenu).add(request.getLobbyPrice());

        // discount
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        Calendar calendar = Calendar.getInstance(timeZone);
        Date now = calendar.getTime();

        List<Discount> discounts = _em.createQuery("SELECT d FROM Discount d WHERE :now > d.discountFromDate " +
                        "AND :now < d.discountToDate", Discount.class)
                .setParameter("now", now)
                .getResultList();
        double totalDiscount = 0.0;
        for (Discount s : discounts){
            totalDiscount += s.getDiscountValue();
        };
        String discountValue = (int)(totalDiscount * 100) + " %";

        // check type customer
        TypeCustomer typeCustomer = _em.createQuery("SELECT t FROM TypeCustomer t, User u " +
                        "WHERE u.id =: id AND t.id = u.typeCustomer.id", TypeCustomer.class)
                .setParameter("id", request.getUserId())
                .getSingleResult();
        String typeCustomerDiscount =  (int)(typeCustomer.getTypeCustomerDiscount() * 100) + " %";

        TotalMoneyResponse result = new TotalMoneyResponse();
        result.setTotal(total);
        result.setServicesFees(servicesFees);
        result.setDiscountTime(discountValue);
        result.setTypeCustomer(typeCustomer.getTypeCustomerName());
        result.setTypeCustomerDiscount(typeCustomerDiscount);

        BigDecimal moneyOrder = total.multiply(new BigDecimal(coefficient.getCoefValue()))
                .setScale(0, RoundingMode.UP);

        BigDecimal totalResult = moneyOrder.subtract(moneyOrder.multiply(new BigDecimal(totalDiscount))).setScale(0, RoundingMode.UP);
        totalResult = totalResult.subtract(moneyOrder.multiply(new BigDecimal(typeCustomer.getTypeCustomerDiscount()))).setScale(0, RoundingMode.UP);

        result.setFinalTotalMoney(totalResult);
        return result;
    }

    private boolean checkExistLobby(int lobId, Date date, String lesson){
        LocalDate bookingDate = LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, date.getDate());;
        int count = _em.createQuery("SELECT o FROM Order o WHERE o.lob.id = :lobId " +
                        "AND o.ordBookingDate = :date AND o.ordBookingLesson = :lesson", Order.class)
                .setParameter("lobId", lobId)
                .setParameter("date", bookingDate)
                .setParameter("lesson", lesson)
                .getResultList().size();

        return count > 0 ? true : false;
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
}
