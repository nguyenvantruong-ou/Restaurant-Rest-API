package com.ou.restaurantmanagement.DTO.Request.Dish;

public class DishUpdateRequestDTO extends DishCreateRequestDTO {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
