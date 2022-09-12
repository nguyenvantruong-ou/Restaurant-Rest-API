package com.ou.restaurantmanagement.Service.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;

public interface LobbyService {
    IBaseResponse getLobby(IBaseRequest req);
}
