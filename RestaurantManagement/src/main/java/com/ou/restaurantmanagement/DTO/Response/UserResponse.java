package com.ou.restaurantmanagement.DTO.Response;

import com.ou.restaurantmanagement.Pojos.User;

import java.util.List;

public class UserResponse {
    private List<User> listUser;
    private int numberPage;

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }
}
