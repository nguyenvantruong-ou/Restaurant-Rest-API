package com.ou.restaurantmanagement.Service.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;

public interface LobbyClientService {
    IBaseResponse getListLobby(IBaseRequest input);
    IBaseResponse getLobbyByID(int lob_id);
    IBaseResponse getLobbyCombobox();
}
