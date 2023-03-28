package com.ou.restaurantmanagement.Service.Admin;

import com.ou.restaurantmanagement.Pojos.Branch;
import com.ou.restaurantmanagement.Repository.Admin.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BranchService {
    List<Branch> ReadBranchs();
}
