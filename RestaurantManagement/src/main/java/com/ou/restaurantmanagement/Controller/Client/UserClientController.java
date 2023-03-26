package com.ou.restaurantmanagement.Controller.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Constant.Role;
import com.ou.restaurantmanagement.DTO.Request.ConfirmRegisterRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.LoginRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.RegisterRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.*;
import com.ou.restaurantmanagement.Pojos.Staff;
import com.ou.restaurantmanagement.Pojos.User;
import com.ou.restaurantmanagement.Service.Admin.StaffService;
import com.ou.restaurantmanagement.Service.Client.UserClientService;
import com.ou.restaurantmanagement.Utils.Jwt.JwtUtil;
import com.ou.restaurantmanagement.Utils.Jwt.UserPrinciple.UserPrinciple;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class UserClientController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private UserClientService _userService;

    @Autowired
    private StaffService _staffService;

    @Value("${jwt.secret-key}")
    private String _SECRET_KEY;

    @Value("${jwt.exp.access-token}")
    private int _expAccessToken;

    @Value("${jwt.exp.refresh-token}")
    private int _expRefreshToken;

    @PostMapping("/client/register")
    @CrossOrigin
    public IBaseResponse register(@RequestParam("file") MultipartFile f, String user){
        try {
            RegisterRequestDTO u;
            try {
                u = new ObjectMapper().readValue(user, RegisterRequestDTO.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            if (f.getOriginalFilename() != "")
                u.setFile(f);

            return _userService.register(u);
        }
        catch (Exception e){
            return new Common(Code.ERROR, null, "Vui lòng kiểm tra lại!");
        }
    }

    @PostMapping("/client/confirm-email")
    @CrossOrigin
    public IBaseResponse confirm(@RequestBody ConfirmRegisterRequestDTO req){
        try {
            return _userService.confirm(req.getUsername(), req.getCode());
        }
        catch (Exception e){
            return new Common(Code.ERROR, null, e.getMessage());
        }
    }
    
    @PostMapping("client/resend-code")
    @CrossOrigin
    public IBaseResponse resendCode(@RequestBody Map<String, String> req){
        try {
            _userService.sendCode(req.get("username"), 0);
            return new Common(Code.OK, null, "Đã gửi mã xác nhận!");
        }
        catch (Exception e){
            return new Common(Code.ERROR, null, "Vui lòng kiểm tra lại!");
        }
    }

    @PostMapping("/login")
    @CrossOrigin
    public IBaseResponse login(@RequestBody LoginRequestDTO login){
        try{
            _userService.checkStatusAccount(login.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtil.createToken(authentication, _expAccessToken);
            String refreshToken = jwtUtil.createToken(authentication, _expRefreshToken);
            UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
            return new Common(Code.OK, new JwtResponse(token, refreshToken,
                    userPrinciple.getId(), userPrinciple.getUsername(),
                    userPrinciple.getAuthorities()), "Đăng nhập thành công");
        }
        catch (Exception e){
            if(e.getMessage().equals("Tài khoản không hoạt động!"))
                return new Common(Code.NOT_FOUND, null, e.getMessage());
            return  new Common(Code.NOT_FOUND, null, "Tên tài khoản hoặc mật khẩu sai! Vui lòng kiểm tra lại!");
        }
    }

    @PostMapping("refresh-token")
    @PreAuthorize("hasAnyAuthority('USER', 'STAFF', 'ADMIN')")
    @CrossOrigin
    public Common RefreshToken(@RequestHeader("Authorization") String authHeader){
        try {
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            Claims claims = Jwts.parser().setSigningKey(_SECRET_KEY)
                    .parseClaimsJws(token).getBody();
            int id = (int) claims.get("userId");
            User credentials = _userService.getProfile(id);
            return new Common(Code.OK,
                    new RefreshTokenResponse(jwtUtil.createNewToken(credentials,_expAccessToken),
                            jwtUtil.createNewToken(credentials, _expRefreshToken)),
                    "Refresh Token thành công");
        }
        catch (Exception e) {
            return new Common(Code.ERROR, null, "Lỗi hệ thống!");
        }
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyAuthority('USER', 'STAFF', 'ADMIN')")
    @CrossOrigin
    public Common getProfile(@RequestHeader("Authorization") String authHeader)
    {
        try
        {
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            Claims claims = Jwts.parser().setSigningKey(_SECRET_KEY)
                    .parseClaimsJws(token).getBody();
            int id = (int) claims.get("userId");
            User user = _userService.getProfile(id);
            Staff staff = new Staff();
            if (user.getUserRole().equals(Role.STAFF))
                staff = _staffService.getStaff(user.getId());

            return new Common( Code.OK, new UserDetailResponse(user, staff),
                      "Lấy thông tin thành công");
        }
        catch (Exception e)
        {
            return new Common(Code.NOT_FOUND, null, e.getMessage());
        }
    }
}
