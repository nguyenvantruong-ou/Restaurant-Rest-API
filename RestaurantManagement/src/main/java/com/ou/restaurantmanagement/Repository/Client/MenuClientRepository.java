package com.ou.restaurantmanagement.Repository.Client;

import com.ou.restaurantmanagement.DTO.Response.MenuResponse;

import java.util.List;

public interface MenuClientRepository{
    List<MenuResponse> getListMenu(String sort);
}
