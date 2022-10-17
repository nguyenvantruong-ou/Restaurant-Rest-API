package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.DTO.Response.HistoryResponse;
import com.ou.restaurantmanagement.DTO.Response.HistoryStaffResponse;
import com.ou.restaurantmanagement.Repository.Client.HistoryClientRepository;
import com.ou.restaurantmanagement.Repository.Client.PaymentClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HistoryClientRepositoryImpl implements HistoryClientRepository {
    @Autowired
    private EntityManager _em;

    @Autowired
    private PaymentClientRepository _orderRepository;

    @Override
    public List<HistoryResponse> getListOrder(int user_id) {
        List<Object[]> listOrder = _em.createQuery("SELECT o.id, l.lobName, o.ordCreatedDate," +
                                "o.ordBookingDate, o.ordBookingLesson, o.ordIsPayment " +
                                "FROM Order o, Lobby l WHERE o.lob.id = l.id AND o.user.id = :user_id " +
                                "order by o.ordIsPayment asc ",
                        Object[].class)
                .setParameter("user_id", user_id)
                .getResultList();
        List<HistoryResponse> results = new ArrayList<>();
        listOrder.forEach(s->{
            HistoryResponse r = new HistoryResponse();
            r.setId((int) s[0]);
            r.setLobbyName((String) s[1]);
            r.setCreateDate((LocalDate) s[2]);
            r.setBookingDate((LocalDate) s[3]);
            r.setLesson((String) s[4]);
            r.setTotalMoney(_orderRepository.totalMoney((int) s[0]));
            r.setPayment((Boolean) s[5]);
            results.add(r);
        });
        return results;
    }

    @Override
    public List<HistoryStaffResponse> getListOrderByStaff(String phoneNumber) {
        List<Object[]> listOrder = _em.createQuery("SELECT o.id, u.userUsername, u.userLastName, " +
                                "u.userFirstName, u.userPhoneNumber, l.lobName, o.ordCreatedDate," +
                                "o.ordBookingDate, o.ordBookingLesson, o.ordIsPayment " +
                                "FROM Order o, Lobby l, User u " +
                                "WHERE o.lob.id = l.id AND o.user.id = u.id AND u.userPhoneNumber LIKE :phoneNumber " +
                                "order by o.ordIsPayment asc ",
                        Object[].class)
                .setParameter("phoneNumber", "%" + phoneNumber + "%")
                .getResultList();
        List<HistoryStaffResponse> results = new ArrayList<>();
        listOrder.forEach(s->{
            HistoryStaffResponse r = new HistoryStaffResponse();
            r.setId((int) s[0]);
            r.setUsername((String) s[1]);
            r.setName(s[2] + " " + s[3]);
            r.setPhoneNumber((String) s[4]);
            r.setLobbyName((String) s[5]);
            r.setCreateDate((LocalDate) s[6]);
            r.setBookingDate((LocalDate) s[7]);
            r.setLesson((String) s[8]);
            r.setTotalMoney(_orderRepository.totalMoney((int) s[0]));
            r.setPayment((Boolean) s[9]);
            results.add(r);
        });
        return results;
    }
}
