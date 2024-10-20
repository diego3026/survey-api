package com.survey.api.services.impl;

import com.survey.api.models.dtos.save.UserUpdate;
import com.survey.api.models.dtos.send.UserResponse;
import com.survey.api.models.entities.User;
import com.survey.api.models.mappers.UserMapper;
import com.survey.api.repositories.UserRepository;
import com.survey.api.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::userToUserResponse).toList();
    }

    @Override
    public UserResponse findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.userToUserResponse(user);
    }

    @Override
    public UserResponse findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.userToUserResponse(user);
    }

    @Override
    public UserResponse update(Long id, UserUpdate userUpdate) {
        User userReceived = userMapper.userUpdateToUser(userUpdate);
        User userFind = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        userFind.updateUser(userReceived);
        return userMapper.userToUserResponse(userRepository.save(userFind));
    }


    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
