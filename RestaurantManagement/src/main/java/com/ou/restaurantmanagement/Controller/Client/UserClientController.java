package com.ou.restaurantmanagement.Controller.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.LoginRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.DTO.Response.JwtResponse;
import com.ou.restaurantmanagement.Utils.Jwt.JwtUtil;
import com.ou.restaurantmanagement.Utils.Jwt.UserPrinciple.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserClientController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;

//    @PostMapping("/register")
//    public IBaseResponse register(@RequestBody ){
//
//    }

    @PostMapping("/login")
    public IBaseResponse login(@RequestBody LoginRequestDTO login){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = null;
            token = jwtUtil.createToken(authentication);
            UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
            return new Common(Code.OK, new JwtResponse(token,userPrinciple.getUsername(),
                    userPrinciple.getAuthorities()), "Đăng nhập thành công");
        }
        catch (Exception e){
            return new Common(Code.INVALID, null, "Tên tài khoản hoặc mật khẩu sai! Vui lòng kiểm tra lại!");
        }
    }
}
