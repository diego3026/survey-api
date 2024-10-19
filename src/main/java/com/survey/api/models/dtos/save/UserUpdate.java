package com.survey.api.models.dtos.save;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserUpdate {
    private String username;
    private String email;
    private String password;
}
