package com.ou.restaurantmanagement.Controller.Client;

import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Client.MenuClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MenuClientController {
    @Autowired
    private MenuClientService _menuService;

    @GetMapping("/get-list-menu-dish")
    @CrossOrigin
    public IBaseResponse getListMenu(){
        return _menuService.getListMenu();
    }
}
