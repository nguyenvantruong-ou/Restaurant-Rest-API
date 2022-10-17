package com.ou.restaurantmanagement.Controller.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.Order.BillRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Order.OrderRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Client.OrderClientService;
import com.ou.restaurantmanagement.Utils.MomoUtil;
import com.ou.restaurantmanagement.Utils.TwilioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/client")
public class OrderClientController {
    @Autowired
    OrderClientService _orderService;

    @PostMapping("/order-lobby")
    @PreAuthorize("hasAnyAuthority('USER')")
    @CrossOrigin
    public IBaseResponse orderLobby(@RequestBody OrderRequestDTO input){
        return _orderService.orderLobby(input);
    }

}
