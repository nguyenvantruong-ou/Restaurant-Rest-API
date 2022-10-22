package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.LobbyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Client.LobbyClientRepository;
import com.ou.restaurantmanagement.Service.Client.LobbyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobbyClientServiceImpl implements LobbyClientService {
    @Autowired
    private LobbyClientRepository _lobbyRepository;

    @Override
    public IBaseResponse getListLobby(IBaseRequest input) {
        LobbyRequestDTO req = (LobbyRequestDTO) input;
        try {
            if(req.getSize() > 0 && req.getPage() > 0){
                return new Common(Code.OK, _lobbyRepository.getListLobby(input),
                        "Lấy danh sách thành công");
            }
            else
                return new Common(Code.INVALID_REQUEST, null, "Request không hợp lệ!");
        }catch (Exception e){
            System.err.println("ERROR: " + e);
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public IBaseResponse getLobbyByID(int lob_id) {
        try {
            if (lob_id > 0)
                return new Common(Code.OK, _lobbyRepository.getLobbyByID(lob_id),
                    "Lấy thông tin thành công");
            return new Common(Code.INVALID_REQUEST, null,
                    "Kiểm tra lại dữ liệu truyền vào!");
        } catch (Exception e){
            System.err.println("ERROR in getLobbyByID: " + e);
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }
}
