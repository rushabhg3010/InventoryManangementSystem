package com.rushabh.InventoryManagementSystem.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j

public class JwtUtils {

    private static final Long EXPIRATION_TIME_IN_MILLISEC = 1000L * 60 * 60 * 24L * 30L * 6L;
    //expires in 6 months in miliseconds

    private SecretKey key;

    @Value("${secreteJwtString}")
    private String secretJwtString;

    @PostConstruct
    private void init() {
        byte[] keyByte= secretJwtString.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keyByte, "HmacSHA256");
    }

    public String generateToken(String email){

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLISEC))
                .signWith(key)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsFunction){
        return claimsFunction.apply(Jwts.parser().verifyWith(key).build().parseClaimsJws(token).getPayload());
    }

    public boolean isValidToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
