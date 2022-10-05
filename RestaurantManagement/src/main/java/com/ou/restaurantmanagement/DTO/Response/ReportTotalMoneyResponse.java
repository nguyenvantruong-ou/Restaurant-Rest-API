package com.ou.restaurantmanagement.DTO.Response;

import java.math.BigDecimal;

public class ReportTotalMoneyResponse {
    private int month;
    private int year;
    private BigDecimal total;

    public ReportTotalMoneyResponse(){}
    public ReportTotalMoneyResponse(int month, int year, BigDecimal total){
        this.month = month;
        this.year = year;
        this.total = total;
    }
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
