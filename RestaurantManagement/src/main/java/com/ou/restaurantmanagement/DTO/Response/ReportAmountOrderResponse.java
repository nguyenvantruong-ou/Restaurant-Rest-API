package com.ou.restaurantmanagement.DTO.Response;

public class ReportAmountOrderResponse {
    private int month;
    private int year;
    private int amountOrder;

    public ReportAmountOrderResponse(){}
    public  ReportAmountOrderResponse(int month, int year, int amountOrder){
        this.month = month;
        this.year = year;
        this.amountOrder = amountOrder;
    }
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
