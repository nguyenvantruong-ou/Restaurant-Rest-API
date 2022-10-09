package com.ou.restaurantmanagement.Controller.Admin;

import com.ou.restaurantmanagement.DTO.Request.ReportRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Admin.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReportController {
    @Autowired
    private ReportService _reportService;

    @GetMapping("/get-list-year-in-bill")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CrossOrigin
    public  IBaseResponse getListYear(){
        return _reportService.listYear();
    }

    @GetMapping("/report-total-money-by-year")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CrossOrigin
    public IBaseResponse reportTotalMoney(@RequestParam Map<String, String> params){
        ReportRequestDTO req  = new ReportRequestDTO(LocalDate.parse(params.get("fromDate")),
                                                    LocalDate.parse(params.get("toDate")));
        return _reportService.reportTotalMoney(req);
    }

    @GetMapping("/report-amount-order-by-year")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CrossOrigin
    public IBaseResponse reportAmount(@RequestParam Map<String, String> params){
        ReportRequestDTO req  = new ReportRequestDTO(LocalDate.parse(params.get("fromDate")),
                LocalDate.parse(params.get("toDate")));
        return _reportService.reportAmountOrder(req);
    }
}
