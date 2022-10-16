package com.ou.restaurantmanagement.Repository.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.Order.BillRequestDTO;
import com.ou.restaurantmanagement.Pojos.Order;

import java.math.BigDecimal;

public interface OrderClientRepository {
    boolean checkLobby(IBaseRequest input);
    boolean addOrder(IBaseRequest input);
    boolean addBill(IBaseRequest input);
    Order getOrderByID(int order_id);
    BigDecimal totalMoney(int order_id);
}
