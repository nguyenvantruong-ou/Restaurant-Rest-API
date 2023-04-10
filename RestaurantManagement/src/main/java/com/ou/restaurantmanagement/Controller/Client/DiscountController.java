package com.ou.restaurantmanagement.Controller.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.Service.Client.DiscountClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class DiscountController {
    @Autowired
    private DiscountClientService _discountService;

    @GetMapping("/discounts")
    public Common ReadDiscounts() {
        try {
            return new Common(Code.OK, _discountService.ReadDiscounts(), "Lấy danh sách thành công");
        }
        catch (Exception e) {
            return new Common(Code.ERROR, null, "Hệ thống có sự cố!");
        }
    }
}
