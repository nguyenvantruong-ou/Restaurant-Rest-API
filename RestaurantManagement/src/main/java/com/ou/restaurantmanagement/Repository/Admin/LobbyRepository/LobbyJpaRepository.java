package com.ou.restaurantmanagement.Repository.Admin.LobbyRepository;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Pojos.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LobbyJpaRepository extends JpaRepository<Lobby, Integer> {

}
