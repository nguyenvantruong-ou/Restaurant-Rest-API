package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.Pojos.Dish;
import com.ou.restaurantmanagement.Repository.Client.DishClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DishClientRepositoryImpl implements DishClientRepository {
    @PersistenceContext
    private EntityManager _em;

    @Override
    public List<Dish> getListDish() {
        return _em.createQuery("SELECT d FROM Dish d WHERE d.dishIsActive = true", Dish.class).getResultList();
    }
}
