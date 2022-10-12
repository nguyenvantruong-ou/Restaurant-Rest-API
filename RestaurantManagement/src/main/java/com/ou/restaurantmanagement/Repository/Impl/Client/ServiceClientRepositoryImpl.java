package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.Pojos.Service;
import com.ou.restaurantmanagement.Repository.Client.ServiceClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ServiceClientRepositoryImpl implements ServiceClientRepository {
    @Autowired
    private EntityManager _em;

    @Override
    public List<Service> getListService() {
        return _em.createQuery("SELECT s FROM Service s WHERE s.serIsActive = true", Service.class)
                .getResultList();
    }
}
