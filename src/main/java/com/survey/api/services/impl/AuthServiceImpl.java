package com.survey.api.services.impl;

import com.survey.api.models.dtos.auth.*;
import com.survey.api.models.dtos.send.UserResponse;
import com.survey.api.models.entities.Role;
import com.survey.api.models.entities.User;
import com.survey.api.models.enums.ERole;
import com.survey.api.models.mappers.AuthMapper;
import com.survey.api.models.mappers.UserMapper;
import com.survey.api.repositories.RoleRepository;
import com.survey.api.repositories.UserRepository;
import com.survey.api.services.AuthService;
import com.survey.api.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtservice;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, AuthMapper authMapper, PasswordEncoder passwordEncoder, JwtService jwtservice, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtservice = jwtservice;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseAuth login(LoginRequest loginRequest) {
        User user = userRepository.findByUsernameOrEmail(loginRequest.getUsername(),loginRequest.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        loginRequest.getPassword()
                )
        );

        var jwtToken = jwtservice.generateToken(user);
        var refreshToken = jwtservice.generateRefreshToken(user);
        TokenResponse tokenResponse = TokenResponse.builder().access(jwtToken).refresh(refreshToken).build();
        return ResponseAuth.builder().user(userMapper.userToUserResponse(user)).token(tokenResponse).build();
    }

    @Override
    public ResponseAuth register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email ya registrado");
        }
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username ya registrado");
        }

        Role role;
        try{
            role = roleRepository.findRoleByName(registerRequest.getRole()).orElseThrow(() -> new RuntimeException("Role not found"));
        } catch (RuntimeException e) {
            role = new Role();
            role.setName(registerRequest.getRole());
            roleRepository.save(role);
        }

        User user = authMapper.registerRequestToUser(registerRequest);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        User userSaved = userRepository.save(user);
        var jwtToken = jwtservice.generateToken(userSaved);
        var refreshToken = jwtservice.generateRefreshToken(userSaved);
        UserResponse userResponse = userMapper.userToUserResponse(userRepository.save(userSaved));
        TokenResponse tokenResponse = TokenResponse.builder().access(jwtToken).refresh(refreshToken).build();
        return ResponseAuth.builder().user(userResponse).token(tokenResponse).build();
    }

    @Override
    public void updateRole(Long idAdmin, UpdateRole updateRole) {
        User userAdmin = userRepository.findById(idAdmin).orElseThrow(()-> new RuntimeException("User Admin not found"));
        if(!userAdmin.getRole().getName().equals(ERole.ADMIN)){
            throw new RuntimeException("User is not admin");
        }

        User user = userRepository.findById(updateRole.getIdUser()).orElseThrow(()->new RuntimeException("User not found"));
        ERole newRole = ERole.valueOf(updateRole.getRole());
        Role roleFind = roleRepository.findRoleByName(newRole).orElseThrow(()-> new RuntimeException("Role not found"));
        user.setRole(roleFind);
        userRepository.save(user);
    }

    @Override
    public TokenResponse refreshToken(String refresh) {
        if(refresh == null){
            throw new RuntimeException("Refresh token is null");
        }

        final String userEmail = jwtservice.extractEmail(refresh);

        if (userEmail == null){
            throw new IllegalArgumentException("Invalid refresh token");
        }

        final User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException(userEmail));

        if(!jwtservice.isTokenValid(refresh,user)){
            throw new IllegalArgumentException("Invalided Refresh token");
        }

        final String token = jwtservice.generateToken(user);
        final String refreshToken = jwtservice.generateRefreshToken(user);
        return TokenResponse.builder().access(token).refresh(refreshToken).build();
    }
}
