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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository _userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public IBaseResponse getUser(IBaseRequest input) {
        UserRequestDTO req = (UserRequestDTO) input;
        Object data = null;
        try {
            if (req.getType().equals("all")) {
                req.setKw("");
                data = _userRepository.getList(req);

            } else {
                int id = Integer.parseInt(req.getType());
                data = _userRepository.getUserByID(id);
            }

            if (data == null)
                return new Common(Code.NOT_FOUND, data, "Tài khoản không tồn tại!");
            return new Common(Code.OK, data, "Tìm kiếm thành công");
        } catch (Exception e){
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public IBaseResponse getUserByName(IBaseRequest input) {
        UserRequestDTO req = (UserRequestDTO) input;
        if(req.getSize() > 0 && req.getPage() > 0 )
        try {
            Object data =  _userRepository.getList(input);
            return new Common(Code.OK, data, "Tìm kiếm thành công");
        }
        catch (Exception e){
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
        else
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
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
    public IBaseResponse updateUserName(IBaseRequest input) {
        UserRequestDTO user = (UserRequestDTO) input;
//        if (!_userRepository.isIdCart(user.getUserIdCard(), user.getId()))
//            return new Common(Code.INVALID, null, "Số CMND đã tồn tại!");
//        else
            if(!_userRepository.isUsername(user.getUserUsename(), user.getId()))
            return new Common(Code.INVALID, null, "Tên tài khoản đã tồn tại!");
            else{  // dữ liệu hợp lệ
                try {
                    if(user.getFile() != null){
                        String link = upImage(user.getFile());
                        if(link == null)
                            return new Common(Code.INVALID, null, "Lưu ảnh không thành công!");
                        user.setUserImage(link);
                    }
                    user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
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

    @Override
    public IBaseResponse getUserByUsername(String username) {
        try {
            return new Common(Code.OK, _userRepository.getUserByUsername(username), "Thành công");
        }catch (Exception e){
            return new Common(Code.NOT_FOUND, null, "Lấy thất bại");
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
