package com.ou.restaurantmanagement.Repository.Client;

import com.ou.restaurantmanagement.Pojos.Dish;

import java.util.List;

public interface DishClientRepository {
    List<Dish> getListDish();
}
