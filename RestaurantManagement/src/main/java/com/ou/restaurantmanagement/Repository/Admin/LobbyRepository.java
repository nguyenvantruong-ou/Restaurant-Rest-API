package com.ou.restaurantmanagement.Repository.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.LobbyResponse;

public interface LobbyRepository {
    LobbyResponse getListLobby(IBaseRequest req);
}
