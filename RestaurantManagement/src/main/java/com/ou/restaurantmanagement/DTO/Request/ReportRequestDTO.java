package com.ou.restaurantmanagement.DTO.Request;

import java.time.LocalDate;

public class ReportRequestDTO implements IBaseRequest{
    private LocalDate fromDate;
    private LocalDate toDate;

    public ReportRequestDTO(){}
    public ReportRequestDTO(LocalDate fd, LocalDate td){
        this.fromDate = fd;
        this.toDate = td;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
