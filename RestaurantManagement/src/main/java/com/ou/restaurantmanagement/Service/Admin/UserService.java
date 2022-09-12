package com.ou.restaurantmanagement.Service.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.UserRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    IBaseResponse getUser(IBaseRequest input);
    IBaseResponse deleteUser(int id);
    IBaseResponse getUserByName(IBaseRequest input);
    IBaseResponse updateUserName(UserRequestDTO user);
}
