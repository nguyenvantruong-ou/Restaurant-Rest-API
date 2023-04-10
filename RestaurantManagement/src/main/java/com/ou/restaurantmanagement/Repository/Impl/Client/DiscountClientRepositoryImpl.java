package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.Pojos.Discount;
import com.ou.restaurantmanagement.Repository.Client.DiscountClientRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class DiscountClientRepositoryImpl implements DiscountClientRepository {
    @PersistenceContext
    private EntityManager _em;

    @Override
    public List<Discount> ReadDiscounts(Date now) {
        return _em.createQuery("SELECT d FROM Discount d WHERE :now > d.discountFromDate " +
                        "AND :now < d.discountToDate", Discount.class)
                .setParameter("now", now)
                .getResultList();
    }
}
