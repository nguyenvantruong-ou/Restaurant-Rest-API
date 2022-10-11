package com.ou.restaurantmanagement.Repository.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.LobbyResponse;

public interface LobbyClientRepository {
    LobbyResponse getListLobby(IBaseRequest input);
}
