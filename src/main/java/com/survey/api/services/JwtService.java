package com.survey.api.services;

import com.survey.api.models.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;

public interface JwtService {
    String extractEmail(String token);
    String generateToken(User user);
    String generateRefreshToken(User user);
    String buildToken(User user, Long expiration);
    boolean isTokenValid(String token, User user);
    boolean isTokenExpired(String token);
}
