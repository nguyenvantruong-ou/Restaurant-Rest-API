package com.ou.restaurantmanagement.Controller.Admin;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.Dish.DishCreateRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.Dish.DishUpdateRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.Service.Admin.DishService;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@CrossOrigin
public class DishController {
    @Autowired
    private DishService _dishService;

    @PostMapping("dish")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Common createDish(@RequestParam("dishImage") MultipartFile dishImage,
                             @RequestParam("dishName") String dishName,
                             @RequestParam("dishDescription") String dishDescription){
        try {
            DishCreateRequestDTO req = new DishCreateRequestDTO(dishName, dishImage, dishDescription);
            _dishService.createDish(req);
            return new Common(Code.OK, null, "Tạo mới thành công");
        }
        catch (Exception e){
            return new Common(Code.ERROR, null, e.getMessage());
        }
    }

    @GetMapping("dish")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Common readDish(@RequestParam String kw)
    {
        try {
            return new Common(Code.OK, _dishService.readDishs(kw), "Thành công");
        }
        catch (Exception e){
            return new Common(Code.ERROR, null, "Vui lòng kiểm tra lại!");
        }
    }

    @PutMapping("dish")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public  Common updateDish(@RequestParam("id") int id,
                              @RequestParam("dishName") String dishName,
                              @RequestParam(name = "dishImage", required = false) MultipartFile file,
                              @RequestParam("dishDescription") String dishDescription){
        try {
            DishUpdateRequestDTO req = new DishUpdateRequestDTO();
            req.setId(id);
            req.setDishName(dishName);
            if(!file.isEmpty())
                req.setDishImage(file);
            else req.setDishImage(null);
            req.setDishDescription(dishDescription);
            _dishService.updateDish(req);
            return new Common(Code.OK, null, "Cập nhật thành công");
        }
        catch (Exception e){
            return new Common(Code.ERROR, e.getMessage(), e.getMessage());
        }
    }

    @DeleteMapping("dish/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Common deleteDish(@PathVariable int id){
        try {
            _dishService.deleteDish(id);
            return new Common(Code.OK, null, "Xóa món ăn thành công");
        }
        catch (Exception e){
            return new Common(Code.ERROR, e.getMessage(), "Vui lòng kiểm tra lại!");
        }
    }
}
