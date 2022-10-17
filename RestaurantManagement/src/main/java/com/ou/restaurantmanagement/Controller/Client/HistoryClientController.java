package com.ou.restaurantmanagement.Controller.Client;

import com.ou.restaurantmanagement.DTO.Request.Order.BillRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Client.HistoryClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class HistoryClientController {
    @Autowired
    private HistoryClientService _historyService;

    @GetMapping("/get-list-history")
    @PreAuthorize("hasAnyAuthority('USER')")
    @CrossOrigin
    public IBaseResponse getListOrder(@RequestParam("user-id") int user_id){
        return _historyService.getListOrder(user_id);
    }

    @GetMapping("/staff-get-all-order")
    @PreAuthorize("hasAnyAuthority('STAFF')")
    @CrossOrigin
    public IBaseResponse getAllOrder(@RequestParam("phone-number") String phoneNumber){
        return _historyService.getListOrderByStaff(phoneNumber);
    }

}
