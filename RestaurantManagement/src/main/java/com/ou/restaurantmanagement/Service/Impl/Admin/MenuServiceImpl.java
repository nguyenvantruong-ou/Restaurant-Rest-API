package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.MenuRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Pojos.Menu;
import com.ou.restaurantmanagement.Repository.Admin.MenuRepository;
import com.ou.restaurantmanagement.Service.Admin.MenuService;
import com.ou.restaurantmanagement.Utils.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository _menuRepository;
    @Override
    public IBaseResponse getListMenu() {
        return new Common(Code.OK, _menuRepository.findAll(),"Lấy danh sách menu thành công");
    }

    @Override
    public IBaseResponse deleteMenu(int id) {
        Menu menu = _menuRepository.findById(id).get();
        if(menu.getMenuIsActive())
            return new Common(Code.INVALID, null, "Menu này đã xóa từ trước");
        else {
            menu.setMenuIsActive(false);

            String message = "Xóa thành công";
            int code = Code.OK;
            try {
                _menuRepository.save(menu);
            } catch (Exception e) {
                message = "Vui lòng kiểm tra lại!";
                code = Code.INVALID;
            }
            return new Common(code, null, message);
        }
    }

    @Override
    public IBaseResponse updateMenu(IBaseRequest input) {
        try {

            MenuRequestDTO req = (MenuRequestDTO) input;

            Menu menu = _menuRepository.findById(req.getId()).get();

            if(req.getFile().getSize() > 0)
                req.setMenuImage(upImage(req.getFile()));
            else return new Common(Code.NOT_FOUND, null, "Vui lòng chọn ảnh!");

            menu.setMenuName(req.getMenuName());
            menu.setMenuPrice(req.getMenuPrice());
            menu.setMenuIsActive(true);
            menu.setMenuImage(req.getMenuImage());
            _menuRepository.save(menu);
            return new Common(Code.OK, null, "Cập nhật menu thành công");
        } catch (Exception e) {
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }

    }
    private static String upImage(MultipartFile f){
        Cloudinary cloudinary = CloudinaryUtil.getCloudinaryClient();
        try {
            Map r = cloudinary.uploader().upload(f.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            return (String) r.get("secure_url");
        } catch (IOException e) {
            return null;
        }
    }
}
