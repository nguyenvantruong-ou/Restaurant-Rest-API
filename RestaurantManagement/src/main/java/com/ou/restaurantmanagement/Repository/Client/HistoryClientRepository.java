package com.ou.restaurantmanagement.Repository.Client;

import com.ou.restaurantmanagement.DTO.Response.HistoryResponse;
import com.ou.restaurantmanagement.DTO.Response.HistoryStaffResponse;

import java.util.List;

public interface HistoryClientRepository {
    List<HistoryResponse> getListOrder(int user_id);
    List<HistoryStaffResponse> getListOrderByStaff(String phoneNumber);

}
