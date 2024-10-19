package com.survey.api.models.dtos.auth;

import com.survey.api.models.enums.ERole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private ERole role;
}
