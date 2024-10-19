package com.survey.api.models.dtos.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {
    private String access;
    private String refresh;
}
