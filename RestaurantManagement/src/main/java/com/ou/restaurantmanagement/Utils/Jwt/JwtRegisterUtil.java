package com.ou.restaurantmanagement.Utils.Jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtRegisterUtil {
    private static String jwtSecret = "secretKeyBytenguyenvantruongquangnamdananasjhfjhgfjheghiwmqreffdsgsfgfghhghjfhjgjdgsfgsdftgdfsgdfggfggg";
    private int jwtExpiration = 15 * 60 * 1000;

    public String CreateToken(int code){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .claim("code", code)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
