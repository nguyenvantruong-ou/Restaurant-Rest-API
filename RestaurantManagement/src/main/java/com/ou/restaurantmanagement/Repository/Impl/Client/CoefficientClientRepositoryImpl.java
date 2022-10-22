package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.Pojos.Coefficient;
import com.ou.restaurantmanagement.Repository.Client.CoefficientClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CoefficientClientRepositoryImpl implements CoefficientClientRepository {

    @Autowired
    private EntityManager _em;

    @Override
    public List<Coefficient> getListCoefficient() {
        return _em.createQuery("SELECT c FROM Coefficient c", Coefficient.class).getResultList();
    }
}
