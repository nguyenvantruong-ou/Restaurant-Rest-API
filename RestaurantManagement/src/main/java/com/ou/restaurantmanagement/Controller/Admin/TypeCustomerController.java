package com.ou.restaurantmanagement.Controller.Admin;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.CreateTypeCustomerRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.TypeCustomerRequest;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.Service.Admin.TypeCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class TypeCustomerController {
    @Autowired
    private TypeCustomerService _typeCusService;

    @GetMapping("type-customer")
    public Common readTypeStaff() {
        try {
            return new Common(Code.OK, _typeCusService.readTypeCustomer(), "Thành công");
        }
        catch (Exception e) {
            return new Common(Code.ERROR, e.getMessage(), "Vui lòng kiểm tra lại!");
        }
    }

    @PostMapping("type-customer")
    public Common createTypeCustomer(@RequestBody CreateTypeCustomerRequestDTO req) {
        try {
            _typeCusService.createTypeCustomer(req);
            return new Common(Code.OK, null, "Thêm mới thành công");
        }
        catch (Exception e) {
            return new Common(Code.ERROR, e.getMessage(), "Vui lòng kiểm tra lại!");
        }
    }

    @PutMapping("type-customer")
    public Common updateTypeStaff(@RequestBody TypeCustomerRequest req) {
        try {
            _typeCusService.updateTypeCustomer(req);
            return new Common(Code.OK, null, "Cập nhật thành công");
        }
        catch (Exception e) {
            return new Common(Code.ERROR, e.getMessage(), "Vui lòng kiểm tra lại!");
        }
    }

    @DeleteMapping("type-customer/{id}")
    public Common deleteTypeCustomer(@PathVariable int id){
        try {
            _typeCusService.deleteTypeCustomer(id);
            return new Common(Code.OK, null, "Xóa thành công");
        }
        catch (Exception e) {
            return new Common(Code.ERROR, e.getMessage(), "Vui lòng kiểm tra lại!");
        }
    }
}
