package com.ou.restaurantmanagement.DTO.Response;

import com.ou.restaurantmanagement.Pojos.Dish;

import java.math.BigDecimal;
import java.util.List;

public class MenuResponse {
    private int id;
    private String menuName;
    private BigDecimal menuPrice;
    private String menuImage;
    private List<Dish> listDish;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public BigDecimal getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(BigDecimal menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

    public List<Dish> getListDish() {
        return listDish;
    }

    public void setListDish(List<Dish> listDish) {
        this.listDish = listDish;
    }
}
