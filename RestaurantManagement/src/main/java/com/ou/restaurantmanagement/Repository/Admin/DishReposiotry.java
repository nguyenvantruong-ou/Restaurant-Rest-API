package com.ou.restaurantmanagement.Repository.Admin;

import com.ou.restaurantmanagement.Pojos.Dish;

import java.util.List;

public interface DishReposiotry {
    void createDish(Dish dish);
    List<Dish> readDishs(String kw);
    void updateDish(Dish dish);
    void deleteDish(int id);
}
