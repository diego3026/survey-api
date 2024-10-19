package com.survey.api.models.mappers;


import com.survey.api.models.dtos.save.UserUpdate;
import com.survey.api.models.dtos.send.UserResponse;
import com.survey.api.models.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userUpdateToUser(UserUpdate userUpdate);
    UserResponse userToUserResponse(User user);
}
