package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.ou.restaurantmanagement.Pojos.Branch;
import com.ou.restaurantmanagement.Repository.Admin.BranchRepository;
import com.ou.restaurantmanagement.Service.Admin.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository _branchRepository;

    @Override
    public List<Branch> ReadBranchs() {
        return _branchRepository.ReadBranchs();
    }
}
