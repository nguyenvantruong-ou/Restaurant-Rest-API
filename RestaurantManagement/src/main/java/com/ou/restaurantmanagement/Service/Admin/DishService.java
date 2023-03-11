package com.ou.restaurantmanagement.Service.Admin;

import com.ou.restaurantmanagement.DTO.Request.Dish.DishUpdateRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Dish.DishCreateRequestDTO;
import com.ou.restaurantmanagement.Pojos.Dish;

import java.util.List;

public interface DishService {
    void createDish(DishCreateRequestDTO dish);
    List<Dish> readDishs();
    void updateDish(DishUpdateRequestDTO dish);
    void deleteDish(int id);
}
