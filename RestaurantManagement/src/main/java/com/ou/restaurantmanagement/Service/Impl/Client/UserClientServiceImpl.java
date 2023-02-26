package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.RegisterRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Pojos.User;
import com.ou.restaurantmanagement.Pojos.UserToken;
import com.ou.restaurantmanagement.Repository.Admin.UserRepository;
import com.ou.restaurantmanagement.Repository.Client.UserClientRepository;
import com.ou.restaurantmanagement.Utils.CloudinaryUtil;
import com.ou.restaurantmanagement.Utils.Jwt.JwtRegisterUtil;
import com.ou.restaurantmanagement.Utils.Jwt.JwtUtil;
import com.ou.restaurantmanagement.Utils.MailUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
@EnableAsync
public class UserClientServiceImpl implements com.ou.restaurantmanagement.Service.Client.UserClientService {
    @Value("${jwt.secret-key}")
    private String _jwtSecret;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserClientRepository _userRepository;

    @Autowired
    private UserRepository _checkValidate;

    @Autowired
    private JwtRegisterUtil _jwtRegisterUtil;

    @Override
    public void checkStatusAccount(String username) {
        if(!_userRepository.getStatusAccount(username))
            throw new IllegalArgumentException("Tài khoản không hoạt động!");
    }

    @Override
    public IBaseResponse register(IBaseRequest input) throws MessagingException, UnsupportedEncodingException {
        RegisterRequestDTO register = (RegisterRequestDTO) input;
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

        int lastestUserId = _userRepository.register(register);

        //send mail
        sendCode(register.getUserUsename(), lastestUserId);

        return new Common(Code.OK, null, "Đã gửi mã xác nhận");
    }

    @Override
    public void sendCode(String username, int flag) throws MessagingException, UnsupportedEncodingException {
        User user = _userRepository.getUserByUsername(username);

        // send mail
        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
        String content = code + " là mã xác nhận của bạn!" +
                " Vui lòng không cung cấp mật mã này cho bất kì ai trong bất kì trường hợp nào.";
        MailUtil mail = new MailUtil();
        mail.SendMail(user.getUserEmail(), content);

        // create new token
        String token = _jwtRegisterUtil.CreateToken(code);

        // create a new userToken
        if(flag > 0){
            UserToken userToken = new UserToken();
            userToken.setId(flag);
            userToken.setUserToken(token);
            userToken.setUserTokenCreatedDate(new Date());
            _userRepository.createUserToken(userToken);
        }
        else {      // update userToken
            UserToken userToken = _userRepository.readUserToken(username);
            userToken.setUserToken(token);
            userToken.setUserTokenCreatedDate(new Date());
            _userRepository.updateUserToken(userToken);
        }
    }

    @Override
    public IBaseResponse confirm(String username, int code) {
        // decode jwt anf compare code
        UserToken userToken = _userRepository.readUserToken(username);
        String token = userToken.getUserToken();

        try {
            Claims claims = Jwts.parser().setSigningKey(_jwtSecret)
                    .parseClaimsJws(token).getBody();
            int jwtCode = (int) claims.get("code");
            if(code == jwtCode){
                _userRepository.setStatusUSer(username);
                return new Common(Code.OK, null, "Đăng ký thành công!");
            }
            return new Common(Code.INVALID, null, "Mã xác nhận không chính xác!");
        }
        catch (Exception e){
            throw new IllegalArgumentException("Đã hết thời gian xác nhận!");
        }
    }

    @Override
    public User getProfile(int id) {
        if (id < 1)
            throw new ArithmeticException("Id phải lớn hơn 0!");
        return _userRepository.getUserById(id);
    }
}
