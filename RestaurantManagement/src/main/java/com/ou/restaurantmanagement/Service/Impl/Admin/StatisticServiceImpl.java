package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Admin.StatisticRepository;
import com.ou.restaurantmanagement.Service.Admin.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticRepository _statisticRepository;

    @Override
    public IBaseResponse getListBill() {
        try {
            return new Common(Code.OK, _statisticRepository.getListBill(), "Lấy danh sách thành công");
        }
        catch (Exception e){
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }
}
