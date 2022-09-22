package com.ou.restaurantmanagement.Service.Admin;

import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;

public interface ReportService {
    IBaseResponse listYear();
    IBaseResponse reportTotalMoney(int year);
    IBaseResponse reportAmountOrder(int year);
}
