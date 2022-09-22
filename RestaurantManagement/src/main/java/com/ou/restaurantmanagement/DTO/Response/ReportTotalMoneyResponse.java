package com.ou.restaurantmanagement.DTO.Response;

import java.math.BigDecimal;

public class ReportTotalMoneyResponse {
    private int month;
    private BigDecimal total;

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
}
