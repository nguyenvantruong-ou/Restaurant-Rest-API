package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.Pojos.Branch;
import com.ou.restaurantmanagement.Repository.Admin.BranchRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BranchRepositoryImpl implements BranchRepository {
    @PersistenceContext
    private EntityManager _em;

    @Override
    public List<Branch> ReadBranchs() {
        return _em.createQuery("SELECT b FROM Branch b", Branch.class).getResultList();
    }
}
