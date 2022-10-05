package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.ReportRequestDTO;
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
    public List<ReportTotalMoneyResponse> reportTotalMoney(IBaseRequest input) {
        ReportRequestDTO req = (ReportRequestDTO) input;
        int fromDay = req.getFromDate().getDayOfMonth();
        int fromMonth = req.getFromDate().getMonthValue();
        int fromYear = req.getFromDate().getYear();

        int toDay = req.getToDate().getDayOfMonth();
        int toMonth = req.getToDate().getMonthValue();
        int toYear = req.getToDate().getYear();

        int cMonth = fromMonth;
        int cYear = fromYear;

        List<ReportTotalMoneyResponse> results = new ArrayList<>();
        do {
            for (int i = cMonth; i <= 12; i++) {
                if(i > toMonth && cYear >= toYear)
                    break;
                Object tp = null;
                if(i == fromMonth && cYear == fromYear){
                    tp = _em.createQuery("SELECT sum(b.billTotalMoney) FROM Bill b " +
                                    "WHERE YEAR (b.billCreatedDate)  = :year AND MONTH(b.billCreatedDate) = :month " +
                                    "AND DAY (b.billCreatedDate) >= :day", Object.class)
                            .setParameter("year", fromYear)
                            .setParameter("month", i)
                            .setParameter("day", fromDay)
                            .getSingleResult();
                }
                else if(i == toMonth && cYear == toYear){
                    tp = _em.createQuery("SELECT sum(b.billTotalMoney) FROM Bill b " +
                                    "WHERE YEAR (b.billCreatedDate)  = :year AND MONTH(b.billCreatedDate) = :month " +
                                    "AND DAY (b.billCreatedDate) <= :day", Object.class)
                            .setParameter("year", toYear)
                            .setParameter("month", i)
                            .setParameter("day", toDay)
                            .getSingleResult();
                }
                else if((cYear < toYear) || (cYear == toYear && i <= toMonth))
                    tp = _em.createQuery("SELECT sum(b.billTotalMoney) FROM Bill b " +
                                    "WHERE YEAR (b.billCreatedDate)  = :year AND MONTH(b.billCreatedDate) = :month", Object.class)
                            .setParameter("year", cYear)
                            .setParameter("month", i)
                            .getSingleResult();

                if(tp != null) {
                    ReportTotalMoneyResponse r = new ReportTotalMoneyResponse(i, cYear, (BigDecimal) tp);
                    results.add(r);
                }
            }
            cMonth = 1;
            cYear ++;
        }
        while (cYear <= toYear );

        return results;
    }

    @Override
    public List<ReportAmountOrderResponse> reportAmountOrder(IBaseRequest input) {
        ReportRequestDTO req = (ReportRequestDTO) input;
        int fromDay = req.getFromDate().getDayOfMonth();
        int fromMonth = req.getFromDate().getMonthValue();
        int fromYear = req.getFromDate().getYear();

        int toDay = req.getToDate().getDayOfMonth();
        int toMonth = req.getToDate().getMonthValue();
        int toYear = req.getToDate().getYear();

        int cMonth = fromMonth;
        int cYear = fromYear;

        List<ReportAmountOrderResponse> results = new ArrayList<>();
        do {
            for (int i = cMonth; i <= 12; i++) {
                if(i > toMonth && cYear >= toYear)
                    break;
                Object tp = null;
                if(i == fromMonth && cYear == fromYear){
                    tp = _em.createQuery("SELECT count(b.id) FROM Bill b " +
                                    "WHERE YEAR (b.billCreatedDate)  = :year AND MONTH(b.billCreatedDate) = :month " +
                                    "AND DAY (b.billCreatedDate) >= :day", Object.class)
                            .setParameter("year", fromYear)
                            .setParameter("month", i)
                            .setParameter("day", fromDay)
                            .getSingleResult();
                }
                else if(i == toMonth && cYear == toYear){
                    tp = _em.createQuery("SELECT count(b.id) FROM Bill b " +
                                    "WHERE YEAR (b.billCreatedDate)  = :year AND MONTH(b.billCreatedDate) = :month " +
                                    "AND DAY (b.billCreatedDate) <= :day", Object.class)
                            .setParameter("year", toYear)
                            .setParameter("month", i)
                            .setParameter("day", toDay)
                            .getSingleResult();
                }
                else if((cYear < toYear) || (cYear == toYear && i <= toMonth))
                    tp = _em.createQuery("SELECT count(b.id) FROM Bill b " +
                                    "WHERE YEAR (b.billCreatedDate)  = :year AND MONTH(b.billCreatedDate) = :month", Object.class)
                            .setParameter("year", cYear)
                            .setParameter("month", i)
                            .getSingleResult();

                if((int)(long)tp > 0) {
                    ReportAmountOrderResponse r = new ReportAmountOrderResponse(i, cYear, (int) (long) tp);
                    results.add(r);
                }
            }
            cMonth = 1;
            cYear ++;
        }
        while (cYear <= toYear );

        return results;
    }

}
