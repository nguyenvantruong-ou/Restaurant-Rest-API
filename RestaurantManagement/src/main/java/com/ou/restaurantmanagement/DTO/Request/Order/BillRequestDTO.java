package com.ou.restaurantmanagement.DTO.Request.Order;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;

public class BillRequestDTO implements IBaseRequest {
    private int userID;
    private int orderID;

    public BillRequestDTO(){}

    public BillRequestDTO(int user_id, int order_id){
        this.userID = user_id;
        this.orderID = order_id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
