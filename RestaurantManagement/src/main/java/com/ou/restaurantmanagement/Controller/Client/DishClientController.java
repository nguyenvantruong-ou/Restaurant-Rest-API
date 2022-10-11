package com.ou.restaurantmanagement.Controller.Client;

import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Client.DishClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DishClientController {
    @Autowired
    private DishClientService _dishService;

    @GetMapping("/get-list-dish")
    public IBaseResponse getListDish(){
        return _dishService.getListDish();
    }
}
