package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Client.CoefficientClientRepository;
import com.ou.restaurantmanagement.Service.Client.CoefficientClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoefficientClientServiceImpl implements CoefficientClientService {
    @Autowired
    private CoefficientClientRepository _coefficientRepository;

    @Override
    public IBaseResponse getListCoefficient() {
        try {
            return new Common(Code.OK, _coefficientRepository.getListCoefficient(),
                    "Lấy danh sách thành công");
        } catch (Exception e){
            System.err.println("ERROR in getListCoefficient: " + e);
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }
}
