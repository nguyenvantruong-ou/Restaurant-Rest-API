package com.ou.restaurantmanagement.Repository.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;

public interface UserClientRepository {
    boolean register(IBaseRequest input);
}
