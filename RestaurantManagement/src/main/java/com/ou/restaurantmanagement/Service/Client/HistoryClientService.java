package com.ou.restaurantmanagement.Service.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;

public interface HistoryClientService {
    IBaseResponse getListOrder(int user_id);
    IBaseResponse getListOrderByStaff(String phoneNumber);
    IBaseResponse confirmPayment(IBaseRequest input);
}
