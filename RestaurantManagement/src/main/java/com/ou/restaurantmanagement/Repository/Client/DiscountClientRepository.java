package com.ou.restaurantmanagement.Repository.Client;

import com.ou.restaurantmanagement.Pojos.Discount;

import java.util.Date;
import java.util.List;

public interface DiscountClientRepository {
    List<Discount> ReadDiscounts(Date now);
}
