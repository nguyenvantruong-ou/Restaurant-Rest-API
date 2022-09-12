package com.ou.restaurantmanagement.Controller.Admin;

import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.UserRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cloudinary.Cloudinary;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService _userService;

    // Lấy dánh danh sách hoặc tìm kiếm
    @GetMapping("/get-user")
    public IBaseResponse getUser(@RequestBody UserRequestDTO req){
        return _userService.getUser(req);
    }

    // Tìm kiếm theo tên
    @GetMapping("/get-user-by-name")
    public IBaseResponse getUserByName(@RequestBody UserRequestDTO req){
        return _userService.getUserByName(req);
    }

    // Xóa user theo id
    @PostMapping("/delete-user")
    public IBaseResponse getUser(@RequestParam(value = "id") int id){
        return _userService.deleteUser(id);
    }

    // sửa thông tin user
    @PostMapping("/update-user")
    public IBaseResponse updateUser(@RequestParam("file") MultipartFile f, String user){
        UserRequestDTO u;
        try {
            u = new ObjectMapper().readValue(user, UserRequestDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if(f.getOriginalFilename() != "")
            u.setFile(f);

        return _userService.updateUserName(u);
    }


}
