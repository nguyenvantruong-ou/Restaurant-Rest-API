package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.Pojos.Staff;
import com.ou.restaurantmanagement.Repository.Admin.StaffRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StaffRepositoryImpl implements StaffRepository {
    @PersistenceContext
    private EntityManager _em;

    @Override
    public Staff getStaff(int id) {
        return _em.createQuery("SELECT s FROM Staff s WHERE s.id = :id", Staff.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
