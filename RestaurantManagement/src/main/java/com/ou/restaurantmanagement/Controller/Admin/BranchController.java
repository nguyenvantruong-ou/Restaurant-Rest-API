package com.ou.restaurantmanagement.Controller.Admin;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.Service.Admin.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class BranchController {
    @Autowired
    private BranchService _branchService;

    @GetMapping("branchs")
    public Common ReadBranchs(){
        try {
            return new Common(Code.OK, _branchService.ReadBranchs(), "Lấy danh sách chi nhánh thành công");
        }
        catch (Exception e) {
            return new Common(Code.ERROR, null, e.getMessage());
        }
    }
}
