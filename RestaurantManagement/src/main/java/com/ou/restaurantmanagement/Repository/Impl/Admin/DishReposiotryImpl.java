package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.Pojos.Dish;
import com.ou.restaurantmanagement.Repository.Admin.DishReposiotry;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DishReposiotryImpl implements DishReposiotry {
    @PersistenceContext
    private EntityManager _em;

    @Override
    public void createDish(Dish dish) {
        _em.persist(dish);
    }

    @Override
    public List<Dish> readDishs(String kw) {
        return _em.createQuery("SELECT dish FROM Dish dish WHERE dish.dishName like :kw", Dish.class)
                .setParameter("kw","%" + kw + "%")
                .getResultList();
    }

    @Override
    public void updateDish(Dish dish) {
        Dish d = _em.createQuery("SELECT dish FROM Dish dish WHERE dish.id = :id", Dish.class)
                .setParameter("id", dish.getId())
                .getSingleResult();

        d.setDishName(dish.getDishName());
        d.setdishDescription(dish.getDishDescription());
        if (dish.getDishImage() != null)
            d.setDishImage(dish.getDishImage());

        _em.merge(d);
    }

    @Override
    public void deleteDish(int id) {
        Dish dish = _em.createQuery("SELECT dish FROM Dish dish WHERE dish.id = :id", Dish.class)
                .setParameter("id", id)
                .getSingleResult();
        dish.setDishIsActive(false);
        _em.merge(dish);
    }
}
