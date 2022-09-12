package com.ou.restaurantmanagement.Controller.Admin;

import com.ou.restaurantmanagement.DTO.Request.LobbyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Admin.LobbyService;
import com.ou.restaurantmanagement.Service.Impl.Admin.LobbyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LobbyController {
    @Autowired
    private LobbyService _lobbyService;

    public LobbyController(){
        _lobbyService = new LobbyServiceImpl();
    }
    @GetMapping("/get-list-lobby")
    public IBaseResponse getListLobby(@RequestBody LobbyRequestDTO req){
        return _lobbyService.getLobby(req);
    }


}
