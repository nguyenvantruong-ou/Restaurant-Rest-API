package com.ou.restaurantmanagement.Repository.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.Pojos.Order;

import java.math.BigDecimal;

public interface PaymentClientRepository {
    boolean addBill(IBaseRequest input);
    Order getOrderByID(int order_id);
    BigDecimal totalMoney(int order_id);
}
