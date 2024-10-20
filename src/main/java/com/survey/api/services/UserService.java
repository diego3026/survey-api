package com.survey.api.services;

import com.survey.api.models.dtos.save.UserUpdate;
import com.survey.api.models.dtos.send.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse findByUsername(String username);
    UserResponse findByEmail(String email);
    UserResponse update(Long id, UserUpdate userUpdate);
    void deleteById(Long id);
    void deleteAll();
}
