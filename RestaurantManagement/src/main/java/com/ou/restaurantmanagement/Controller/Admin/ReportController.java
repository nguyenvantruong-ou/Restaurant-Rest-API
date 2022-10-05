package com.ou.restaurantmanagement.Controller.Admin;

import com.ou.restaurantmanagement.DTO.Request.ReportRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Admin.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ReportController {
    @Autowired
    private ReportService _reportService;

    @GetMapping("/get-list-year-in-bill")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public  IBaseResponse getListYear(){
        return _reportService.listYear();
    }

    @GetMapping("/report-total-money-by-year")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public IBaseResponse reportTotalMoney(@RequestBody ReportRequestDTO req){
        return _reportService.reportTotalMoney(req);
    }

    @GetMapping("/report-amount-order-by-year")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public IBaseResponse reportAmount(@RequestBody ReportRequestDTO req){
        return _reportService.reportAmountOrder(req);

//        return new Common(1, req.getFromDate().getDayOfMonth(), "");
    }
}
