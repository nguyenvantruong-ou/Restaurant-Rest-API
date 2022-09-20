package com.ou.restaurantmanagement.Service.Admin;


import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;

public interface ServiceService {
    IBaseResponse getListService(String kw);
    IBaseResponse deleteService(int id);
    IBaseResponse createService(IBaseRequest input);
    IBaseResponse update(IBaseRequest input);
}
