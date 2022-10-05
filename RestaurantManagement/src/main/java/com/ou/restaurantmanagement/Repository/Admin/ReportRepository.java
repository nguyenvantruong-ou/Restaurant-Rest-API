package com.ou.restaurantmanagement.Repository.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.ReportAmountOrderResponse;
import com.ou.restaurantmanagement.DTO.Response.ReportTotalMoneyResponse;

import java.util.List;

public interface ReportRepository {
    List<Integer> getListYear();
    List<ReportTotalMoneyResponse> reportTotalMoney(IBaseRequest input);
    List<ReportAmountOrderResponse> reportAmountOrder(IBaseRequest input);
}
