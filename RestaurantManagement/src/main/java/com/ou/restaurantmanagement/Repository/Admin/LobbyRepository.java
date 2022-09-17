package com.ou.restaurantmanagement.Repository.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.LobbyResponse;
import com.ou.restaurantmanagement.Pojos.Lobby;

public interface LobbyRepository {
    LobbyResponse getListLobby(IBaseRequest req);
    boolean createLobby(IBaseRequest input);
    Lobby getLobbyNew();
    boolean deleteLobby(int id);
    boolean updateLobby(IBaseRequest input);
}
