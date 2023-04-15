package com.ou.restaurantmanagement.Repository.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.Order.BillRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Order.TotalMoneyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.TotalMoneyResponse;
import com.ou.restaurantmanagement.Pojos.Lobby;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderClientRepository {
    boolean checkLobby(IBaseRequest input);
    boolean addOrder(IBaseRequest input);
    List<Lobby> getListLobbiesByDate(Date date, String lesson);
    TotalMoneyResponse calculateTotalAmount(TotalMoneyRequestDTO request);
}
