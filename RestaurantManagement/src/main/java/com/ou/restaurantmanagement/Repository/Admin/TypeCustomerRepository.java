package com.ou.restaurantmanagement.Repository.Admin;

import com.ou.restaurantmanagement.DTO.Request.CreateTypeCustomerRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.TypeCustomerRequest;
import com.ou.restaurantmanagement.Pojos.TypeCustomer;

import java.util.List;

public interface TypeCustomerRepository {
    List<TypeCustomer> readTypeCustomer();
    void createTypeCustomer(TypeCustomer req);
    void updateTypeCustomer(TypeCustomerRequest req);
    void deleteTypeCustomer(int id);
}
