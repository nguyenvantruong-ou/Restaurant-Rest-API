package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.RegisterRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Admin.UserRepository;
import com.ou.restaurantmanagement.Repository.Client.UserClientRepository;
import com.ou.restaurantmanagement.Utils.CloudinaryUtil;
import com.ou.restaurantmanagement.Utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class UserClientService implements com.ou.restaurantmanagement.Service.Client.UserClientService {
    private static RegisterRequestDTO register;
    private static int code;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserClientRepository _userRepository;

    @Autowired
    private UserRepository _checkValidate;
    @Override
    public IBaseResponse register(IBaseRequest input) {
        register = (RegisterRequestDTO) input;
        // validate
        if(!_checkValidate.isIdCart(register.getUserIdCard(), 0))
            return new Common(Code.INVALID_REQUEST, null, "CMND/CCCD đã tồn tại!");
        if(!_checkValidate.isUsername(register.getUserUsename(), 0))
            return new Common(Code.INVALID_REQUEST, null, "Tên tài khoản đã tồn tại!");
        // password
        register.setUserPassword(passwordEncoder.encode(register.getUserPassword()));

        // up image
        if(register.getFile() != null)
            register.setUserImage(CloudinaryUtil.upImage(register.getFile()));

        // send mail
        try {
            String vocative =(register.getUserSex()) ? "anh" : "chị";
            code = (int) Math.floor(((Math.random() * 899999) + 100000));
            String content = code + " là mã xác nhận của "+ vocative +
                    "! Vui lòng không cung cấp mật mã này cho bất kì ai trong bất kì trường hợp nào.";
            MailUtil.SendMail(register.getUserEmail(), content);
            return new Common(Code.OK, null, "Đã gửi mã xác nhận");
        } catch (MessagingException e) {
            System.out.println("Error: " + e);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error: " + e);
        }

        return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
    }

    @Override
    public IBaseResponse confirm(int code) {
        if(code == this.code){
            if(_userRepository.register(this.register))
                return new Common(Code.OK, null, "Đăng ký thành công");
            else
                return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
        }
        return new Common(Code.INVALID, null, "Mã xác nhận sai!");
    }
}
