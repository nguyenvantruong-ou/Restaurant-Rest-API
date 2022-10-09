package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Client.MenuClientRepository;
import com.ou.restaurantmanagement.Service.Client.MenuClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuClientServiceImpl implements MenuClientService {
    @Autowired
    private MenuClientRepository _menuRepository;

    @Override
    public IBaseResponse getListMenu() {
        try {
            return new Common(Code.OK, _menuRepository.getListMenu(), "Lấy danh sách thành công");
        }catch (Exception e){
            System.err.println(e);
            return new Common(Code.NOT_FOUND, null, "vui lòng kiểm tra lại!");
        }
    }
}
