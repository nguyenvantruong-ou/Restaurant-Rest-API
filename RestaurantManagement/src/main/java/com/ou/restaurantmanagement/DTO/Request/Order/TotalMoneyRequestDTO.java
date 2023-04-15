package com.ou.restaurantmanagement.DTO.Request.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TotalMoneyRequestDTO {
    private int userId;
    private BigDecimal lobbyPrice;
    private List<Integer> listServices;
    private List<OrderMenuRequestDTO> listMenus;
    private LocalDate bookingDate;
    private String lesson;

    public BigDecimal getLobbyPrice() {
        return lobbyPrice;
    }

    public void setLobbyPrice(BigDecimal lobbyPrice) {
        this.lobbyPrice = lobbyPrice;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public List<Integer> getListServices() {
        return listServices;
    }

    public void setListServices(List<Integer> listServices) {
        this.listServices = listServices;
    }

    public List<OrderMenuRequestDTO> getListMenus() {
        return listMenus;
    }

    public void setListMenus(List<OrderMenuRequestDTO> listMenus) {
        this.listMenus = listMenus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
