package com.ou.restaurantmanagement.Utils.Jwt.UserPrinciple;

import com.ou.restaurantmanagement.Pojos.User;
import com.ou.restaurantmanagement.Repository.Admin.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        return UserPrinciple.build(user);
    }
}
