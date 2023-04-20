package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Constant.Role;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.LoginSocailRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.RegisterRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.DTO.Response.JwtResponse;
import com.ou.restaurantmanagement.Pojos.TypeCustomer;
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

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    @Value("${mail.body.register}")
    private String _content;

    @Value("${avatar_default}")
    private String _avatar;

    @Value("${jwt.exp.access-token}")
    private int _expAccessToken;

    @Value("${jwt.exp.refresh-token}")
    private int _expRefreshToken;

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
        String errorPW = checkPassword(register.getUserPassword());
        if(errorPW != null)
            return new Common(Code.INVALID_REQUEST, null, errorPW);

//        if(!_checkValidate.isIdCart(register.getUserIdCard(), 0))
//            return new Common(Code.INVALID_REQUEST, null, "CMND/CCCD đã tồn tại!");
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

    public String checkPassword(String password) {
        // Kiểm tra mật khẩu có độ dài hợp lệ
        if (password.length() < 6) {
            return "Độ dài mật khẩu phải từ 6 kí tự trở lên!";
        }

        // Kiểm tra mật khẩu có chứa ít nhất một kí tự đặc biệt
        Pattern specialCharPattern = Pattern.compile("[!@#$%^&*()_+\\[\\]{};':\"\\\\|,.<>/?]");
        Matcher specialCharMatcher = specialCharPattern.matcher(password);
        if (!specialCharMatcher.find()) {
            return "Mật khẩu phải chứa ít nhất một kí tự đặc biệt!";
        }

        // Kiểm tra mật khẩu có chứa ít nhất một chữ cái viết hoa
        Pattern uppercaseLetterPattern = Pattern.compile("[A-Z]");
        Matcher uppercaseLetterMatcher = uppercaseLetterPattern.matcher(password);
        if (!uppercaseLetterMatcher.find()) {
            return "Mật khẩu phải chứa ít nhất một chữ hoa!";
        }

        // Nếu mật khẩu đáp ứng tất cả các yêu cầu, trả về true
        return null;
    }

    @Override
    public void sendCode(String username, int flag) throws MessagingException, UnsupportedEncodingException {
        User user = _userRepository.getUserByUsername(username);

        // send mail
        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
//        String content = String.format("<p> <span style='color: red'>%d</span> là mã xác nhận của bạn!" +
//                " Vui lòng không cung cấp mật mã này cho bất kì ai trong bất kì trường hợp nào.</p>", code);
        MailUtil mail = new MailUtil();
        mail.SendMail(user.getUserEmail(), String.format(_content, code));

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

    @Override
    public Common loginSocial(LoginSocailRequestDTO request) {
        if(request.getUsername() == null || request.getPassword() == null
        || request.getEmail() == null || request.getFirstName() == null)
            return new Common(Code.INVALID_REQUEST, null, "Bad Request!");

        User u = new User();
        User uRes = _userRepository.getUserByUsername(request.getUsername());
        if(uRes == null){
            u.setUserUsename(request.getUsername());
            u.setUserPassword(passwordEncoder.encode(request.getPassword()));
            u.setUserEmail(request.getEmail());
            u.setUserLastName(request.getLastName());
            u.setUserFirstName(request.getFirstName());
            u.setUserImage(_avatar);
            u.setUserRole(Role.USER);
            u.setUserSex(true);

            u.setUserIsActive(true);
            u.setUserIdCard("");
            u.setUserPhoneNumber("");
            u.setUserAddress("Việt Nam");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 1900);
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            Date date = calendar.getTime();
            u.setUserDateOfBirth(date);
            u.setUserJoinedDate(new Date());

            TypeCustomer t = new TypeCustomer();
            t.setId(1);
            u.setTypeCustomer(t);

            uRes = _userRepository.loginSocial(u);
        }


        JwtUtil jwt = new JwtUtil();
        String accessToken = jwt.createNewToken(uRes, _expAccessToken);
        String refreshToken = jwt.createNewToken(uRes, _expRefreshToken);

        JwtResponse result = new JwtResponse(accessToken, refreshToken, uRes.getId(),u.getUserUsename(), u.getUserRole());
        return new Common(Code.OK, result, "Đăng nhập thành công");
    }
}
