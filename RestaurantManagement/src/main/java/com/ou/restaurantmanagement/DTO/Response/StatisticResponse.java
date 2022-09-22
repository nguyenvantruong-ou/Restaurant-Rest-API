package com.ou.restaurantmanagement.DTO.Response;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

public class StatisticResponse implements  IBaseResponse{
    private Integer id;
    private LocalDate createDate;
    private String userName;
    private BigDecimal totalMoney;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
