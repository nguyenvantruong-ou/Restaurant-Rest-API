package com.ou.restaurantmanagement.Controller.Client;

import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Client.CoefficientClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class CoefficientClientController {
    @Autowired
    private CoefficientClientService _coefficientService;

    @GetMapping("/get-list-coefficient")
    @CrossOrigin
    public IBaseResponse getListCoefficient(){
        return _coefficientService.getListCoefficient();
    }
}
