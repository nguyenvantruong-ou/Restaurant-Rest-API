package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Admin.ReportRepository;
import com.ou.restaurantmanagement.Service.Admin.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository _reportRepository;

    @Override
    public IBaseResponse listYear() {
        return new Common(Code.OK, _reportRepository.getListYear(), "Thành công");
    }

    @Override
    public IBaseResponse reportTotalMoney(IBaseRequest input) {
        try {
            return new Common(Code.OK, _reportRepository.reportTotalMoney(input), "Thành công");
        }
        catch (Exception e){
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public IBaseResponse reportAmountOrder(IBaseRequest input) {
            try {
                return new Common(Code.OK, _reportRepository.reportAmountOrder(input), "Thành công");
            }
            catch (Exception e){
                return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
            }
    }
}
