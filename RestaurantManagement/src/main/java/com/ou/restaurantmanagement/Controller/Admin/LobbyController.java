package com.ou.restaurantmanagement.Controller.Admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.restaurantmanagement.DTO.Request.LobbyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Admin.LobbyService;
import com.ou.restaurantmanagement.Service.Impl.Admin.LobbyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        return _lobbyService.ReadLobby(req);
    }

    @PostMapping("/create-lobby")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public IBaseResponse createLobby(@RequestParam("file") MultipartFile file,
                                     @RequestParam("listFile") List<MultipartFile> listFile,
                                     String lobby){
        LobbyRequestDTO req ;
        try {
            req = new ObjectMapper().readValue(lobby, LobbyRequestDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        req.setLobImage(file);
        req.setListImage(listFile);
        return _lobbyService.createLobby(req);
    }

    @PostMapping("/delete-lobby")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public IBaseResponse deleteLobby(@RequestParam(value = "id") int id){
        return _lobbyService.deleteLobby(id);
    }

    @PostMapping("/update-lobby")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public IBaseResponse updateLobby(@RequestParam("file") MultipartFile file,
                                     @RequestParam("listFile") List<MultipartFile> listFile,
                                     String lobby){
        LobbyRequestDTO req ;
        try {
            req = new ObjectMapper().readValue(lobby, LobbyRequestDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        req.setLobImage(file);
        req.setListImage(listFile);
        return _lobbyService.updateLobby(req);
    }
}
