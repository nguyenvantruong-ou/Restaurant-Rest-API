package com.ou.restaurantmanagement.Repository.Admin;


import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.Pojos.Service;

import java.util.List;

public interface ServiceRepository{
    List<Service> getService(String kw);
    boolean deleteService(int id);
    boolean createService(IBaseRequest input);
    boolean updateService(IBaseRequest input);
}
