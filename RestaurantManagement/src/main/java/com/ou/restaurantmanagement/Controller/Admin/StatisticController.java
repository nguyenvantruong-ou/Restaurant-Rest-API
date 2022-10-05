package com.ou.restaurantmanagement.Controller.Admin;

import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Admin.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StatisticController {
    @Autowired
    private StatisticService _statisticService;

    @GetMapping("/get-list-bill")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public IBaseResponse getListBill(){
        return _statisticService.getListBill();
    }
}
