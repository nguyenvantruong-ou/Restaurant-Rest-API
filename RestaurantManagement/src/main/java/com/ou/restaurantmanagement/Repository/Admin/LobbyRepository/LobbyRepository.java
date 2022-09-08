package com.ou.restaurantmanagement.Repository.Admin.LobbyRepository;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;

public interface LobbyRepository {
    public IBaseResponse GetLobby(IBaseRequest req);
}
