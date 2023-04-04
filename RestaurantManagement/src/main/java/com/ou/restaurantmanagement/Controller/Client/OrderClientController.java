package com.ou.restaurantmanagement.Controller.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.Order.BillRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Order.DateLessonRequest;
import com.ou.restaurantmanagement.DTO.Request.Order.OrderRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Client.OrderClientService;
import com.ou.restaurantmanagement.Utils.MomoUtil;
import com.ou.restaurantmanagement.Utils.TwilioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/client")
public class OrderClientController {
    @Autowired
    OrderClientService _orderService;

    @PostMapping("/order-lobby")
    @PreAuthorize("hasAnyAuthority('USER')")
    @CrossOrigin
    public IBaseResponse orderLobby(@RequestBody OrderRequestDTO input){
        return _orderService.orderLobby(input);
    }

    @GetMapping("/order/get-list-lobby")
//    @PreAuthorize("hasAnyAuthority('USER')")
    @CrossOrigin
    public IBaseResponse orderGetLobbies(@RequestParam("bookingdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date bookingDate,
                                         @RequestParam("lesson") String lesson){
        DateLessonRequest request = new DateLessonRequest();
        request.setBookingDate(bookingDate);
        request.setLesson(lesson);
        try {
            return new Common(Code.OK, _orderService.getLobbiesOrder(request), "Thành công!");
        }
        catch (Exception e){
            return new Common(Code.ERROR, e.getMessage(), "Vui lòng kiểm tra lại!");
        }
    }
}
