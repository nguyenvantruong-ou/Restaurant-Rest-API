package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Request.CreateTypeCustomerRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.TypeCustomerRequest;
import com.ou.restaurantmanagement.Pojos.TypeCustomer;
import com.ou.restaurantmanagement.Repository.Admin.TypeCustomerRepository;
import com.ou.restaurantmanagement.Service.Admin.TypeCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCustomerServiceImpl implements TypeCustomerService {
    @Autowired
    private TypeCustomerRepository _typeCusRepo;

    @Override
    public List<TypeCustomer> readTypeCustomer() {
        return _typeCusRepo.readTypeCustomer();
    }

    @Override
    public void createTypeCustomer(CreateTypeCustomerRequestDTO req) {
        if(req.getTypeCusName().length() < 1)
            throw new IllegalArgumentException("Bad request!");

        TypeCustomer tc = new TypeCustomer();
        tc.setTypeCustomerName(req.getTypeCusName());
        tc.setTypeCustomerDiscount(req.getTypeCusDiscount());
        tc.setTypeCustomerPoints(req.getTypeCusPoints());
        tc.setTypeCustomerStatus(true);

        _typeCusRepo.createTypeCustomer(tc);
    }

    @Override
    public void updateTypeCustomer(TypeCustomerRequest req) {
        if (req.getId() < 1 || req.getTypeCusName().length() < 1)
            throw new IllegalArgumentException("Bad request!");
        _typeCusRepo.updateTypeCustomer(req);
    }

    @Override
    public void deleteTypeCustomer(int id) {
        if(id < 1)
            throw new IllegalArgumentException("Id must be greater than 0!");

        _typeCusRepo.deleteTypeCustomer(id);
    }
}
