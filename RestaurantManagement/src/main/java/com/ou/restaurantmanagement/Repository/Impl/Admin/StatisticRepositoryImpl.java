package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Response.StatisticResponse;
import com.ou.restaurantmanagement.Pojos.Bill;
import com.ou.restaurantmanagement.Repository.Admin.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class StatisticRepositoryImpl implements StatisticRepository {
    @Autowired
    private EntityManager _em;

    @Override
    public List<StatisticResponse> getListBill() {
        List<Object[]> tp = _em.createQuery("SELECT b.id, u.userUsername, b.billCreatedDate, " +
                "b.billTotalMoney, b.billNote as id FROM Bill b, User u  " +
                "WHERE b.user_id = u.id ", Object[].class).getResultList();
        List<StatisticResponse> results = new ArrayList<>();

        tp.forEach(s->{
            StatisticResponse r = new StatisticResponse();
            r.setId((int) s[0]);
            r.setUserName((String) s[1]);
            r.setCreateDate((LocalDate) s[2]);
            r.setTotalMoney((BigDecimal) s[3]);
            r.setNote((String) s[4]);
            results.add(r);
        });
        return results;
    }
}
