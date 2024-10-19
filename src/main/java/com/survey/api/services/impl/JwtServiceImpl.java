package com.survey.api.services.impl;

import com.survey.api.models.entities.User;
import com.survey.api.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${spring.application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${spring.application.security.jwt.expiration}")
    private Long jwtExpiration;

    @Value("${spring.application.security.jwt.refresh-token.expiration}")
    private Long refreshTokenExpiration;

    @Override
    public String extractEmail(String token) {
        final Claims jwtToken = Jwts.parser().verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtToken.getSubject();
    }

    @Override
    public String generateToken(User user) {
        return buildToken(user,jwtExpiration);
    }

    @Override
    public String generateRefreshToken(User user) {
        return buildToken(user,refreshTokenExpiration);
    }

    @Override
    public String buildToken(User user,Long expiration) {
        return Jwts
                .builder()
                .id(user.getId().toString())
                .claim("username",user.getUsername())
                .claim("role",user.getRole())
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public boolean isTokenValid(String token, User user) {
        final String email = extractEmail(token);
        return (email.equals(user.getEmail()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Claims getClaim(String token){
        final Claims claims=getAllClaims(token);
        return claims;
    }

    private Date getExpiration(String token){
        return getClaim(token).getExpiration();
    }

    public boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
}
