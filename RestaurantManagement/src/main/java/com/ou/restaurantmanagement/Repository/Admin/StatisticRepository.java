package com.ou.restaurantmanagement.Repository.Admin;


import com.ou.restaurantmanagement.DTO.Response.StatisticResponse;
import com.ou.restaurantmanagement.Pojos.Bill;

import java.util.List;

public interface StatisticRepository {
    List<StatisticResponse> getListBill();
}
