package com.ou.restaurantmanagement.Controller.Admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.restaurantmanagement.DTO.Request.MenuRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Admin.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
public class MenuController {
    @Autowired
    private MenuService _menuService;

    @GetMapping("/get-list-menu")
    @CrossOrigin
    public IBaseResponse getListMenu(){
        return _menuService.getListMenu();
    }

    @GetMapping("/delete-menu")
    @CrossOrigin
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public IBaseResponse deleteMenu(@RequestParam(value = "id") int id){
        return _menuService.deleteMenu(id);
    }

    @PostMapping("/update-menu")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CrossOrigin
    public IBaseResponse updateMenu(@RequestParam("file")MultipartFile file, String menu){
        MenuRequestDTO req;
        try {
            req = new ObjectMapper().readValue(menu, MenuRequestDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        req.setFile(file);
        return _menuService.updateMenu(req);
    }
}
