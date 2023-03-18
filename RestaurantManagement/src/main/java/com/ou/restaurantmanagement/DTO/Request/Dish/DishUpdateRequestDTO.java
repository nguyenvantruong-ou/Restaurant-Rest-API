package com.ou.restaurantmanagement.DTO.Request.Dish;

public class DishUpdateRequestDTO extends DishCreateRequestDTO {
    private int id;
    private Boolean dishStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getDishStatus() {
        return this.dishStatus;
    }

    public void setDishStatus(Boolean dishStatus) {
        this.dishStatus = dishStatus;
    }
}
