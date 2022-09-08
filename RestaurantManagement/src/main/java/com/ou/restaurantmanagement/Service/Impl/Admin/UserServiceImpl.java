package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.UserRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Admin.UserRepository;
import com.ou.restaurantmanagement.Repository.Impl.Admin.UserRepositoryImpl;
import com.ou.restaurantmanagement.Service.Admin.UserService;
import com.ou.restaurantmanagement.Utils.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository _userRepository;

    @Override
    public IBaseResponse getUser(IBaseRequest input) {
        Object data = null;
        try {
            UserRequestDTO req = (UserRequestDTO) input;
            if (req.getType().equals("all")) {
                data = _userRepository.getlist(""); // ?

            } else {
                int id = Integer.parseInt(req.getType());
                data = _userRepository.getUserByID(id);
            }

            if (data == Optional.empty())
                return new Common(Code.NOT_FOUND, data, "Tài khoản không tồn tại!");
            return new Common(Code.OK, data, "Tìm kiếm thành công");
        } catch (Exception e){
            return new Common(Code.NOT_FOUND, data, "Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public IBaseResponse getUserByName(String kw) {
        try {
            Object data =  _userRepository.getlist(kw);
            return new Common(Code.OK, data, "Tìm kiếm thành công");
        }
        catch (Exception e){
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public IBaseResponse deleteUser(int id){
        try{
            boolean flag = _userRepository.deleteUser(id);
            if(flag)
                return new Common(Code.OK, null, "Đã xóa thành công");
            else
                return new Common(Code.INVALID, null, "Tài khoản đã xóa trước đó!");
        }
        catch (Exception e) {
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }


    @Override
    public IBaseResponse updateUserName(UserRequestDTO user) {
        if (!_userRepository.isIdCart(user.getUserIdCard(), user.getId()))
            return new Common(Code.INVALID, null, "Số CMND đã tồn tại!");
        else if(!_userRepository.isUsername(user.getUserUsename(), user.getId()))
            return new Common(Code.INVALID, null, "Tên tài khoản đã tồn tại!");
            else{  // dữ liệu hợp lệ
                try {
                    if(user.getFile() != null){
                        String link = upImage(user.getFile());
                        if(link == null)
                            return new Common(Code.INVALID, null, "Lưu ảnh không thành công!");
                        user.setUserImage(link);
                    }
                    if (_userRepository.updateUser(user))
                        return new Common(Code.OK, null,"Đã sửa thành công" );
                    else
                        return new Common(Code.NOT_FOUND, null, "Sửa thất bại!");
                }
                catch (Exception e){
                    return new Common(Code.NOT_FOUND, null, "Sửa thất bại!");
                }
            }
    }

    private String upImage(MultipartFile f){
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
