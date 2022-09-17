package com.ou.restaurantmanagement.Service.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;

public interface LobbyService {
    IBaseResponse ReadLobby(IBaseRequest input);
    IBaseResponse createLobby(IBaseRequest input);
    IBaseResponse deleteLobby(int id);
    IBaseResponse updateLobby(IBaseRequest input);
}
