package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Request.Dish.DishUpdateRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Dish.DishCreateRequestDTO;
import com.ou.restaurantmanagement.Pojos.Dish;
import com.ou.restaurantmanagement.Repository.Admin.DishReposiotry;
import com.ou.restaurantmanagement.Service.Admin.DishService;
import com.ou.restaurantmanagement.Utils.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishReposiotry _dishRepository;

    @Override
    public void createDish(DishCreateRequestDTO dish) {
        String linkImage = "";
        if(!dish.getDishImage().isEmpty())
            linkImage = CloudinaryUtil.upImage(dish.getDishImage());
        else
            throw new IllegalArgumentException("Vui lòng chọn ảnh!");

        _dishRepository.createDish(convertDish(dish.getDishName(), linkImage, dish.getDishDescription()));
    }

    @Override
    public List<Dish> readDishs(String kw) {
        return _dishRepository.readDishs(kw);
    }

    @Override
    public void updateDish(DishUpdateRequestDTO dish) {
        if (dish.getId() < 1)
            throw new IllegalArgumentException("Id phải lớn hơn 0!");

        if (dish.getDishName().length() < 1)
            throw new IllegalArgumentException("Tên món ăn phải lớn hơn 0!");

        String linkImage = "";
        if(dish.getDishImage() != null)
            linkImage = CloudinaryUtil.upImage(dish.getDishImage());

        Dish d = convertDish(dish.getDishName(),
                linkImage ,
                dish.getDishDescription());
        d.setId(dish.getId());
        d.setDishIsActive(dish.getDishStatus());
        _dishRepository.updateDish(d);
    }

    @Override
    public void deleteDish(int id) {
        if (id < 1)
            throw new IllegalArgumentException("Id phải lớn hơn 0!");
        _dishRepository.deleteDish(id);
    }

    private Dish convertDish(String dishName, String dishImage, String dishDescription){
        Dish dish = new Dish();
        dish.setDishName(dishName);
        if(dishImage.length() > 0)
            dish.setDishImage(dishImage);
        dish.setdishDescription(dishDescription);
        dish.setDishIsActive(true);
        return dish;
    }
}
