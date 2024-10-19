package com.survey.api.services;

import com.survey.api.models.dtos.auth.*;

public interface AuthService {
    ResponseAuth login(LoginRequest loginRequest);
    ResponseAuth register(RegisterRequest registerRequest);
    void updateRole(Long idUser, UpdateRole updateRole);
    TokenResponse refreshToken(String refresh);
}
