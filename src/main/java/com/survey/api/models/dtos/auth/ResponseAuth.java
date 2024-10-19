package com.survey.api.models.dtos.auth;

import com.survey.api.models.dtos.send.UserResponse;
import com.survey.api.models.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseAuth {
    private UserResponse user;
    private TokenResponse token;
}
