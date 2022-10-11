package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Client.DishClientRepository;
import com.ou.restaurantmanagement.Service.Client.DishClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishClientServiceImpl implements DishClientService {
    @Autowired
    private DishClientRepository _dishRepository;

    @Override
    public IBaseResponse getListDish() {
        try {
            return new Common(Code.OK, _dishRepository.getListDish(),
                    "Lấy danh sách món ăn thành công");
        }catch (Exception e){
            System.err.println("ERROR: " + e);
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }
}
