package com.ou.restaurantmanagement.DTO.Response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Dictionary;
import java.util.List;

public class JwtResponse {
    private String token;
    private String refreshToken;
    private int userID;
    private String username;
    private String roles;

    public JwtResponse(String token, String refreshToken, int user_id, String username, Collection<? extends GrantedAuthority> roles) {
        this.setToken(token);
        this.setRefreshToken(refreshToken);
        this.setUserID(user_id);
        this.setUsername(username);
        this.setRoles(roles.stream().toList().get(0).toString());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
