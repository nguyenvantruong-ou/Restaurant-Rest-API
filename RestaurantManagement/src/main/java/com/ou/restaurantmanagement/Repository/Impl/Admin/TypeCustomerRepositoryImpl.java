package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Request.CreateTypeCustomerRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.TypeCustomerRequest;
import com.ou.restaurantmanagement.Pojos.TypeCustomer;
import com.ou.restaurantmanagement.Repository.Admin.TypeCustomerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TypeCustomerRepositoryImpl implements TypeCustomerRepository {
    @PersistenceContext
    private EntityManager _em;

    @Override
    public List<TypeCustomer> readTypeCustomer() {
        return _em.createQuery("SELECT t FROM TypeCustomer t ", TypeCustomer.class)
                .getResultList();
    }

    @Override
    public void createTypeCustomer(TypeCustomer req) {
        _em.persist(req);
    }

    @Override
    public void updateTypeCustomer(TypeCustomerRequest req) {
        TypeCustomer tc = _em.createQuery("SELECT t FROM TypeCustomer t WHERE t.id = :id", TypeCustomer.class)
                .setParameter("id", req.getId())
                .getSingleResult();

        tc.setTypeCustomerName(req.getTypeCusName());
        tc.setTypeCustomerPoints(req.getTypeCusPoints());
        tc.setTypeCustomerDiscount(req.getTypeCusDiscount());
        tc.setTypeCustomerStatus(req.getTypeCusStatus());

        _em.merge(tc);
    }

    @Override
    public void deleteTypeCustomer(int id) {
        TypeCustomer tc = _em.createQuery("SELECT tc FROM TypeCustomer tc WHERE tc.id = :id", TypeCustomer.class)
                .setParameter("id", id)
                .getSingleResult();
        tc.setTypeCustomerStatus(false);

        _em.merge(tc);
    }
}
