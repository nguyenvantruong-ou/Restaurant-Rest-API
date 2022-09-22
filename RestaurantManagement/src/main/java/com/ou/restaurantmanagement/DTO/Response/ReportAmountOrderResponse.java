package com.ou.restaurantmanagement.DTO.Response;

public class ReportAmountOrderResponse {
    private int month;
    private int amountOrder;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getAmountOrder() {
        return amountOrder;
    }

    public void setAmountOrder(int amountOrder) {
        this.amountOrder = amountOrder;
    }
}
