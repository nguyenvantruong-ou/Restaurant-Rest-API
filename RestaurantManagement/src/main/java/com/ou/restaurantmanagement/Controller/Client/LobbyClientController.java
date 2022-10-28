package com.ou.restaurantmanagement.Controller.Client;

import com.ou.restaurantmanagement.DTO.Request.LobbyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Client.LobbyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/client")
public class LobbyClientController {
    @Autowired
    private LobbyClientService _lobbyService;

    @GetMapping("/get-list-lobby")
    @CrossOrigin
    public IBaseResponse getListLobby(@RequestParam Map<String, String> params){
        LobbyRequestDTO req = new LobbyRequestDTO();
        req.setSize(Integer.valueOf(params.get("size")));
        req.setPage(Integer.valueOf(params.get("page")));
        req.setKw(params.get("kw"));

        return _lobbyService.getListLobby(req);
    }

    @GetMapping("/get-lobby-by-id")
    @CrossOrigin
    public IBaseResponse getLobbyByID(@RequestParam("id") int lob_id){
        return _lobbyService.getLobbyByID(lob_id);
    }

    @GetMapping("/get-list-lobby-combobox")
    @CrossOrigin
    public IBaseResponse getLobbyCombobox(){
        return _lobbyService.getLobbyCombobox();
    }
}
