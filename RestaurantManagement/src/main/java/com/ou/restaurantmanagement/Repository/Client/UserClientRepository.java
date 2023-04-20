package com.ou.restaurantmanagement.Repository.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.Pojos.User;
import com.ou.restaurantmanagement.Pojos.UserToken;

public interface UserClientRepository {
    boolean getStatusAccount(String username);
    int register(IBaseRequest input);
    void setStatusUSer(String username);
    User getUserByUsername(String username);
    void createUserToken(UserToken userToken);
    void updateUserToken(UserToken userToken);
    UserToken readUserToken(String username);
    User getUserById(int id);
    User loginSocial(User u);
//    User checkLogin(String username, String pw);
}
