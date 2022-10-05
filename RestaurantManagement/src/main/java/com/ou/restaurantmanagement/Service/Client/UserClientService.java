package com.ou.restaurantmanagement.Service.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;

public interface UserClientService {
    IBaseResponse register(IBaseRequest input);
    IBaseResponse confirm(int code);
}
