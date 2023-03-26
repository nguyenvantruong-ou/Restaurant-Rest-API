package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.ou.restaurantmanagement.Pojos.Staff;
import com.ou.restaurantmanagement.Repository.Admin.StaffRepository;
import com.ou.restaurantmanagement.Service.Admin.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffRepository _repoStaff;

    @Override
    public Staff getStaff(int id) {
        return _repoStaff.getStaff(id);
    }
}
