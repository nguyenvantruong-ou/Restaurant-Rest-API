package com.ou.restaurantmanagement.Controller.Admin;

import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Admin.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReportController {
    @Autowired
    private ReportService _reportService;

    @GetMapping("/get-list-year-in-bill")
    public  IBaseResponse getListYear(){
        return _reportService.listYear();
    }

    @GetMapping("/report-total-money-by-year")
    public IBaseResponse reportTotalMoney(@RequestParam(value = "year") int year){
        return _reportService.reportTotalMoney(year);
    }

    @GetMapping("/report-amount-order-by-year")
    public IBaseResponse reportAmount(@RequestParam(value = "year") int year){
        return _reportService.reportAmountOrder(year);
    }
}
