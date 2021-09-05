package com.example.demo.security.jwt;

import com.example.demo.entities.Student;
import com.example.demo.entities.Teacher;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private  static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private String jwtSecret = "SecretKey";
    private int jwtExpirationsMs = 86400000;

    public String generateJwtToken(Authentication authentication, int isTeacher){
        if (isTeacher == 1){
            Teacher userPrincipal = (Teacher) authentication.getPrincipal();
            logger.info(jwtSecret);
            logger.info(String.valueOf(jwtExpirationsMs));
            return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtExpirationsMs))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
        } else {
            Student userPrincipal = (Student) authentication.getPrincipal();
            logger.info(jwtSecret);
            logger.info(String.valueOf(jwtExpirationsMs));
            return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtExpirationsMs))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
        }
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
