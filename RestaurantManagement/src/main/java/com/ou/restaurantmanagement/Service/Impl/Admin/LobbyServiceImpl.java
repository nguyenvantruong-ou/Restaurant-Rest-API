package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Admin.LobbyRepository.LobbyJpaRepository;
import com.ou.restaurantmanagement.Repository.Admin.LobbyRepository.LobbyRepository;
import com.ou.restaurantmanagement.Service.Admin.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobbyServiceImpl implements LobbyService {
    @Autowired
    private LobbyJpaRepository _jpa;
    @Autowired
    private LobbyRepository _lobbyRep;
    @Override
    public IBaseResponse test(){
        return _lobbyRep.GetLobby(new IBaseRequest() {
        });
    }
}
