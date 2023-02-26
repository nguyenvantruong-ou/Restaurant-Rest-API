package com.ou.restaurantmanagement.Service.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Pojos.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface UserClientService {
    void checkStatusAccount(String username);
    IBaseResponse register(IBaseRequest input) throws MessagingException, UnsupportedEncodingException;
    IBaseResponse confirm(String username, int code);
    void sendCode(String username, int userId) throws MessagingException, UnsupportedEncodingException;
    User getProfile(int id);
}
