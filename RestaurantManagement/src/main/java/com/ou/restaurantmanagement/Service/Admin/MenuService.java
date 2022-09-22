package com.ou.restaurantmanagement.Service.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;

public interface MenuService {
    IBaseResponse getListMenu();
    IBaseResponse deleteMenu(int id);
    IBaseResponse updateMenu(IBaseRequest input);
}
