package com.ou.restaurantmanagement.DTO.Request.Dish;


import com.sun.istack.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class DishCreateRequestDTO {
    @NotNull
    private String dishName;

    private MultipartFile dishImage;

    @NotNull
    private String dishDescription;

    public  DishCreateRequestDTO(){}

    public DishCreateRequestDTO(String name, MultipartFile file, String description){
        setDishName(name);
        setDishImage(file);
        setDishDescription(description);
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public MultipartFile getDishImage() {
        return dishImage;
    }

    public void setDishImage(MultipartFile dishImage) {
        this.dishImage = dishImage;
    }
}
