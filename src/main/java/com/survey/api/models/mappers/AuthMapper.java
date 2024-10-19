package com.survey.api.models.mappers;

import com.survey.api.models.dtos.auth.LoginRequest;
import com.survey.api.models.dtos.auth.RegisterRequest;
import com.survey.api.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    User loginRequestToUser(LoginRequest loginRequest);
    @Mapping(source = "role", target = "role.name")
    User registerRequestToUser(RegisterRequest registerRequest);
}
