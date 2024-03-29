package com.ou.restaurantmanagement.DTO.Request.Order;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class OrderRequestDTO implements IBaseRequest {
    private LocalDate ord_booking_date;
    private String ord_booking_lesson;
    private int user_id;
    private int lob_id;
    private List<OrderServiceRequestDTO> listService;
    private List<OrderMenuRequestDTO> listMenu;
    private BigDecimal totalMoney;

    public LocalDate getOrd_booking_date() {
        return ord_booking_date;
    }

    public void setOrd_booking_date(LocalDate ord_booking_date) {
        this.ord_booking_date = ord_booking_date;
    }

    public String getOrd_booking_lesson() {
        return ord_booking_lesson;
    }

    public void setOrd_booking_lesson(String ord_booking_lesson) {
        this.ord_booking_lesson = ord_booking_lesson;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLob_id() {
        return lob_id;
    }

    public void setLob_id(int lob_id) {
        this.lob_id = lob_id;
    }

    public List<OrderServiceRequestDTO> getListService() {
        return listService;
    }

    public void setListService(List<OrderServiceRequestDTO> listService) {
        this.listService = listService;
    }

    public List<OrderMenuRequestDTO> getListMenu() {
        return listMenu;
    }

    public void setListMenu(List<OrderMenuRequestDTO> listMenu) {
        this.listMenu = listMenu;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
