package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.HistoryResponse;
import com.ou.restaurantmanagement.DTO.Response.HistoryStaffResponse;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Client.HistoryClientRepository;
import com.ou.restaurantmanagement.Repository.Client.OrderClientRepository;
import com.ou.restaurantmanagement.Service.Client.HistoryClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryClientServiceImpl implements HistoryClientService {
    @Autowired
    private HistoryClientRepository _historyRepository;

    @Autowired
    private OrderClientRepository _orderRepository;

    @Override
    public IBaseResponse getListOrder(int user_id) {
        try{
            List<HistoryResponse> results = _historyRepository.getListOrder(user_id);
            if(results.size() > 0)
                return new Common(Code.OK, results ,"Lấy danh sách thành công");
            else
                return new Common(Code.OK, null, "Không có hóa đơn nào!");
        } catch (Exception e){
            System.err.println("ERROR in getListOrder(): " + e);
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public IBaseResponse getListOrderByStaff(String phoneNumber) {
        try{
            List<HistoryStaffResponse> results = _historyRepository.getListOrderByStaff(phoneNumber);
            if(results.size() > 0)
                return new Common(Code.OK, results ,"Lấy danh sách thành công");
            else
                return new Common(Code.OK, null, "Không có hóa đơn nào!");
        } catch (Exception e){
            System.err.println("ERROR in getListOrderByStaff(): " + e);
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public IBaseResponse confirmPayment(IBaseRequest input) {
        try{
            if(_orderRepository.addBill(input))
                return new Common(Code.OK, null, "Xác nhận thanh toán thành công!");
            return new Common(Code.INVALID_REQUEST, null, "Vui lòng kiểm tra lại!");
        } catch (Exception e){
            System.err.println("ERROR in confirmPayment(): " + e);
            return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
        }
    }
}
