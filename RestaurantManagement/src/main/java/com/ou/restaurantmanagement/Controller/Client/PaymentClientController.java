package com.ou.restaurantmanagement.Controller.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ou.restaurantmanagement.DTO.Request.Order.BillRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Client.PaymentClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/api/client")
public class PaymentClientController {
    @Autowired
    private PaymentClientService _paymentService;

    @PostMapping("/payment-momo")
    @PreAuthorize("hasAnyAuthority('USER')")
    @CrossOrigin
    public IBaseResponse payMomo(@RequestBody BillRequestDTO input) throws NoSuchAlgorithmException, InvalidKeyException, ExecutionException, JsonProcessingException, InterruptedException {
        return _paymentService.paymentMomo(input);
    }

    @PostMapping("/add-bill-for-momo")
    @CrossOrigin
    public IBaseResponse addbill(@RequestParam("user-id") int user_id,
                                @RequestParam("order-id") int order_id){
        BillRequestDTO req = new BillRequestDTO(user_id, order_id);
        return _paymentService.confirmPayment(req);
    }

    @PostMapping("/staff-confirm-user-payment")
    @PreAuthorize("hasAnyAuthority('STAFF')")
    @CrossOrigin
    public IBaseResponse confirmPayment(@RequestBody BillRequestDTO input){
        return _paymentService.confirmPayment(input);
    }

    @PostMapping("/payment-success")
    @PreAuthorize("hasAnyAuthority('USER', 'STAFF')")
    @CrossOrigin
    public IBaseResponse paymentSuccess(){
        return _paymentService.paymentSuccess();
    }
}
