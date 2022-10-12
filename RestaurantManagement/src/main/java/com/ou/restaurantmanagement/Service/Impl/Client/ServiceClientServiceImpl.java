package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Client.ServiceClientRepository;
import com.ou.restaurantmanagement.Service.Client.ServiceClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClientServiceImpl implements ServiceClientService {
    @Autowired
    private ServiceClientRepository _serviceRepository;

    @Override
    public IBaseResponse getListService() {
        try{
            return new Common(Code.OK, _serviceRepository.getListService(),
                    "Lấy danh sách dịch vụ thành công");
        }catch (Exception e){
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }
}
