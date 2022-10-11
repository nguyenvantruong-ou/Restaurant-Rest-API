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
    public IBaseResponse getListMenu(String sort) {
        try {
            if(sort.trim().length() > 0) {
                sort = sort.trim().toLowerCase();
                return new Common(Code.OK, _menuRepository.getListMenu(sort), "Lấy danh sách thành công");
            }
            return new Common(Code.INVALID_REQUEST, null, "Request không hợp lệ");
        }catch (Exception e){
            System.err.println(e);
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }

}
