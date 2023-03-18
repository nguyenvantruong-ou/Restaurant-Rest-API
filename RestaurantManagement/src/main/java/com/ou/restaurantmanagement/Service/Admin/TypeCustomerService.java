package com.ou.restaurantmanagement.Service.Admin;

import com.ou.restaurantmanagement.DTO.Request.CreateTypeCustomerRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.TypeCustomerRequest;
import com.ou.restaurantmanagement.Pojos.TypeCustomer;

import java.util.List;

public interface TypeCustomerService {
    List<TypeCustomer> readTypeCustomer();
    void createTypeCustomer(CreateTypeCustomerRequestDTO req);
    void updateTypeCustomer(TypeCustomerRequest req);
    void deleteTypeCustomer(int id);
}
