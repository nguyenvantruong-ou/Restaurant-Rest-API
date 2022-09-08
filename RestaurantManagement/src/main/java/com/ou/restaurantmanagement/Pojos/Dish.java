package com.ou.restaurantmanagement.Pojos;


import javax.persistence.*;

@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id", nullable = false)
    private Integer id;

    @Column(name = "dish_name", nullable = false)
    private String dishName;

    @Column(name = "dish_image")
    private String dishImage;

    @Column(name = "dish_is_active")
    private Boolean dishIsActive;

    @Column(name = "dish_description")
    private String dishDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishImage() {
        return dishImage;
    }

    public void setDishImage(String dishImage) {
        this.dishImage = dishImage;
    }

    public Boolean getDishIsActive() {
        return dishIsActive;
    }

    public void setDishIsActive(Boolean dishIsActive) {
        this.dishIsActive = dishIsActive;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setdishDescription(String dishDescription){
        this.dishDescription = dishDescription;
    }
}