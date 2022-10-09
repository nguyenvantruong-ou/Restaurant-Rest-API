package com.ou.restaurantmanagement.Repository.Client;

import com.ou.restaurantmanagement.DTO.Response.MenuResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuClientRepository{
    List<MenuResponse> getListMenu();
}
