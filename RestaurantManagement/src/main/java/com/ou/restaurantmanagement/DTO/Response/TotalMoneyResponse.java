package com.ou.restaurantmanagement.DTO.Response;

import java.math.BigDecimal;

public class TotalMoneyResponse {
    private BigDecimal total;
    private String servicesFees; // phi dich vu
    private String discountTime;
    private String typeCustomer;
    private String typeCustomerDiscount;
    private BigDecimal finalTotalMoney;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDiscountTime() {
        return discountTime;
    }

    public void setDiscountTime(String discountTime) {
        this.discountTime = discountTime;
    }

    public String getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(String typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    public String getTypeCustomerDiscount() {
        return typeCustomerDiscount;
    }

    public void setTypeCustomerDiscount(String typeCustomerDiscount) {
        this.typeCustomerDiscount = typeCustomerDiscount;
    }

    public BigDecimal getFinalTotalMoney() {
        return finalTotalMoney;
    }

    public void setFinalTotalMoney(BigDecimal finalTotalMoney) {
        this.finalTotalMoney = finalTotalMoney;
    }

    public String getServicesFees() {
        return servicesFees;
    }

    public void setServicesFees(String servicesFees) {
        this.servicesFees = servicesFees;
    }
}
