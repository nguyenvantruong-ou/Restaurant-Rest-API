package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.LobbyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Admin.LobbyRepository;
import com.ou.restaurantmanagement.Service.Admin.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobbyServiceImpl implements LobbyService {
    @Autowired
    private LobbyRepository _lobbyRep;
    @Override
    public IBaseResponse getLobby(IBaseRequest req) {
        LobbyRequestDTO input = (LobbyRequestDTO) req;
        if(input.getSize() > 0 && input.getPage() > 0)
            return new Common(Code.OK, _lobbyRep.getListLobby(input), "Tìm kiếm thành công");
        return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
    }
}
