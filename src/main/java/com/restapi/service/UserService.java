package com.restapi.service;

import com.restapi.dto.AuthDto;
import com.restapi.dto.AuthDto;
import com.restapi.exception.common.AppException;
import com.restapi.exception.common.InvalidUserException;
import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.LoginRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthDto authDto;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthResponse register(RegisterRequest registerRequest) {
        AppUser appUser = authDto.mapToAppUser(registerRequest);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(roleRepository.findByName(Role.ADMIN));
        appUser = userRepository.save(appUser);
        return authDto.mapToAuthResponse(appUser);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        AppUser appUser = userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidUserException("Invalid user credentials"));

        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {
            throw new InvalidUserException("Invalid password");
        }

        return authDto.mapToAuthResponse(appUser);
    }
}
