package com.ou.restaurantmanagement.DTO.Response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private String token;
    private int userID;
    private String username;
    private Collection<?extends GrantedAuthority> roles;

    public JwtResponse(String token, int user_id, String username, Collection<? extends GrantedAuthority> roles) {
        this.setToken(token);
        this.setUserID(user_id);
        this.setUsername(username);
        this.setRoles(roles);
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

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
