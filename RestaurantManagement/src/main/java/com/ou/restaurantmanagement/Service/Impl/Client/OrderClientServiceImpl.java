package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.Order.BillRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Order.OrderRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Client.OrderClientRepository;
import com.ou.restaurantmanagement.Service.Client.OrderClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderClientServiceImpl implements OrderClientService {
    @Autowired
    private OrderClientRepository _orderRepository;

    @Override
    public IBaseResponse orderLobby(IBaseRequest input) {
        try{
            OrderRequestDTO req = (OrderRequestDTO) input;
            if(req.getOrd_booking_lesson() != null)
                req.setOrd_booking_lesson(req.getOrd_booking_lesson().trim().toLowerCase());
            if(!_orderRepository.checkLobby(req))
                return new Common(Code.INVALID, null, "Sảnh đã có người đặt trước! Vui lòng chọn sảnh khác hoặc thời gian khác");
            else{
                // order
                if (_orderRepository.addOrder(input))
                    return new Common(Code.OK, null, "Đặt sảnh thành công");
                else
                    return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
            }
        }catch (Exception e){
            System.err.println("ERROR: " + e);
            return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
        }
    }

}
