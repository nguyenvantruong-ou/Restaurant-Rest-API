package com.ou.restaurantmanagement.Repository.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.UserRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.DTO.Response.UserResponse;
import com.ou.restaurantmanagement.Pojos.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    UserResponse getList(IBaseRequest req);
    User getUserByID(int id);
    boolean deleteUser(int userID);
    boolean isIdCart(String idCart, int id);
    boolean isUsername(String username, int id);
    String checkRole(int role);
    boolean updateUser(UserRequestDTO user);
    User getUserByUsername(String username);
}
