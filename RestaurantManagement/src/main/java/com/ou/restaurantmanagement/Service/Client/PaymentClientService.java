package com.ou.restaurantmanagement.Service.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

public interface PaymentClientService {
    IBaseResponse paymentMomo(IBaseRequest input) throws NoSuchAlgorithmException, InvalidKeyException, ExecutionException, JsonProcessingException, InterruptedException;
    IBaseResponse confirmPayment(IBaseRequest input);
    IBaseResponse paymentSuccess();
}
