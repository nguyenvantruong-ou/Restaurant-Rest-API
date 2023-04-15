package com.ou.restaurantmanagement.Service.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.Order.DateLessonRequest;
import com.ou.restaurantmanagement.DTO.Request.Order.TotalMoneyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.DTO.Response.TotalMoneyResponse;
import com.ou.restaurantmanagement.Pojos.Lobby;

import java.util.List;

public interface OrderClientService {
    IBaseResponse orderLobby(IBaseRequest input);
    List<Lobby> getLobbiesOrder(DateLessonRequest request);
    Common totalMoney(TotalMoneyRequestDTO request);
}
