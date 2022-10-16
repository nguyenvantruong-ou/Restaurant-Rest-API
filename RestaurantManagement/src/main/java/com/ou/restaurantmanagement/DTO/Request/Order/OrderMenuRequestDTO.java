package com.ou.restaurantmanagement.DTO.Request.Order;

public class OrderMenuRequestDTO {
    private int menuID;
    private int amountTable;

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getAmountTable() {
        return amountTable;
    }

    public void setAmountTable(int amountTable) {
        this.amountTable = amountTable;
    }
}
