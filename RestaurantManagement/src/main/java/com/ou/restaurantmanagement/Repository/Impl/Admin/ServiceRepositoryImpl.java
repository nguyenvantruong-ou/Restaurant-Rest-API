package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.ServiceRequestDTO;
import com.ou.restaurantmanagement.Pojos.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ServiceRepositoryImpl implements com.ou.restaurantmanagement.Repository.Admin.ServiceRepository {
    @PersistenceContext
    private EntityManager _em;

    @Override
    public List<Service> getService(String kw) {
        TypedQuery<Service> tp = _em.createQuery("SELECT s FROM Service s  WHERE s.serName LIKE :kw", Service.class)
                .setParameter("kw", "%"+kw+"%");
        return tp.getResultList();
    }

    @Override
    public boolean deleteService(int id) {
        TypedQuery<Service> tp = _em.createQuery("SELECT s FROM Service s  WHERE s.id = :id", Service.class)
                .setParameter("id", id);
        Service s = tp.getSingleResult();
        if (!s.getSerIsActive())
            return false;
        s.setSerIsActive(false);
        _em.merge(s);
        return true;
    }

    @Override
    public boolean createService(IBaseRequest input) {
        try {
            ServiceRequestDTO req = (ServiceRequestDTO) input;
            Service s = new Service();
            s.setSerName(req.getSerName());
            s.setSerPrice(req.getSerPrice());
            s.setSerDescription(req.getSerDescription());
            s.setSerImage(req.getSerImage());
            s.setSerIsActive(true);
            _em.persist(s);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateService(IBaseRequest input) {
        try {
            ServiceRequestDTO req = (ServiceRequestDTO) input;
            TypedQuery<Service> tp = _em.createQuery("SELECT s FROM Service s  WHERE s.id = :id", Service.class)
                    .setParameter("id", req.getId());
            Service s = tp.getSingleResult();
            s.setSerName(req.getSerName());
            s.setSerPrice(req.getSerPrice());
            s.setSerDescription(req.getSerDescription());
            if (req.getSerIsActive() != null)
                s.setSerIsActive(req.getSerIsActive());
            if(req.getSerImage() != null)
                s.setSerImage(req.getSerImage());
            _em.merge(s);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
