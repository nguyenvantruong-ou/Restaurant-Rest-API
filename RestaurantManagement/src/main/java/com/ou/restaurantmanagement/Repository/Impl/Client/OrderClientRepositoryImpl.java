package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.Order.OrderMenuRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Order.OrderRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Order.OrderServiceRequestDTO;
import com.ou.restaurantmanagement.Pojos.*;
import com.ou.restaurantmanagement.Repository.Client.OrderClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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
