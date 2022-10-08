package com.ou.restaurantmanagement.Utils.Jwt.UserPrinciple;

import com.ou.restaurantmanagement.Pojos.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserPrinciple implements UserDetails {
    private int id;
    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> role;

    public UserPrinciple(int id, String username, String password,Collection<? extends GrantedAuthority> role) {
        this.setId(id);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static UserPrinciple build(User user){
//        Set<String> roles = new HashSet<>();
//        roles.add(user.getUserRole());
//        List<GrantedAuthority> authorities =
//                roles.stream().map(role ->
//                        new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole()));
        return new UserPrinciple(
                user.getId(),
                user.getUserUsename(),
                user.getUserPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
