package com.ou.restaurantmanagement.Utils.Jwt;

import com.ou.restaurantmanagement.Utils.Jwt.UserPrinciple.UserPrinciple;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private static String jwtSecret = "secretKeyBytenguyenvantruongquangnamdananasjhfjhgfjheghiwmqreffdsgsfgfghhghjfhjgjdgsfgsdftgdfsgdfggfggg";
    private int jwtExpiration = 60 * 24 * 24 * 1000;
    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .claim("roles", userPrinciple.getAuthorities().toString())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e){
            logger.error("Invalid JWT signature -> Message: {}", e);
        } catch (MalformedJwtException e){
            logger.error("Invalid Token format -> Message: {}",e);
        } catch (UnsupportedJwtException e){
            logger.error("Unsupported JWT token -> Message: {}",e);
        } catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty -Message {}",e);
        } catch (ExpiredJwtException e){
            logger.error("JWT Token is expired -> Message: {}",e);
        }
        return false;
    }
    public String getUserNameFromToken(String token){
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }

    public void setSecret(String secret){
        this.jwtSecret = secret;
    }
}
