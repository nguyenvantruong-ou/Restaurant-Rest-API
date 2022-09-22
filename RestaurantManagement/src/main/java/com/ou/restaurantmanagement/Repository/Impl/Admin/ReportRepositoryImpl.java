package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Response.ReportAmountOrderResponse;
import com.ou.restaurantmanagement.DTO.Response.ReportTotalMoneyResponse;
import com.ou.restaurantmanagement.Repository.Admin.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportRepositoryImpl implements ReportRepository {
    @Autowired
    private EntityManager _em;

    @Override
    public List<Integer> getListYear() {
        List<Integer> list = _em.createQuery("SELECT distinct year(b.billCreatedDate) FROM Bill b", Integer.class).getResultList();
        return list;
    }

    @Override
    public List<ReportTotalMoneyResponse> reportTotalMoney(int year) {
        List<ReportTotalMoneyResponse> results = new ArrayList<>();
        for(int i = 1; i <= 12; i ++) {
            BigDecimal tp = _em.createQuery("SELECT sum(b.billTotalMoney) FROM Bill b " +
                            "WHERE YEAR (b.billCreatedDate)  = :year AND MONTH(b.billCreatedDate) = :month", BigDecimal.class)
                    .setParameter("year", year)
                    .setParameter("month", i)
                    .getSingleResult();
            ReportTotalMoneyResponse r = new ReportTotalMoneyResponse();
            r.setMonth(i);
            if (tp == null )
                r.setTotal(BigDecimal.ZERO);
            else
                r.setTotal(tp);
            results.add(r);
        }
        return results;
    }

    @Override
    public List<ReportAmountOrderResponse> reportAmountOrder(int year) {
        List<ReportAmountOrderResponse> results = new ArrayList<>();
        for(int i = 1; i <= 12; i ++) {
            Object tp = _em.createQuery("SELECT count(b.id) FROM Bill b " +
                            "WHERE YEAR (b.billCreatedDate)  = :year AND MONTH(b.billCreatedDate) = :month", Object.class)
                    .setParameter("year", year)
                    .setParameter("month", i)
                    .getSingleResult();
            ReportAmountOrderResponse r = new ReportAmountOrderResponse();
            r.setMonth(i);
            r.setAmountOrder((int)(long)tp);
            results.add(r);
        }
        return results;
    }

}
