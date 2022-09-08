package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Admin.LobbyRepository.LobbyRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LobbyRepositoryImpl implements LobbyRepository {
    @Override
    public IBaseResponse GetLobby(IBaseRequest req) {
        return new Common(201, "OK", "OKKKKK");
    }
}
