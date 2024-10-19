package com.survey.api.models.dtos.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateRole {
    private Long idUser;
    private String role;
}
