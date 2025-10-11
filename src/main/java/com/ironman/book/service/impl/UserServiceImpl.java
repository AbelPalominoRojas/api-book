package com.ironman.book.service.impl;

import com.ironman.book.common.security.JwtService;
import com.ironman.book.config.AppProperties;
import com.ironman.book.dto.user.LoginRequest;
import com.ironman.book.dto.user.UserCreateRequest;
import com.ironman.book.dto.user.UserSecurityResponse;
import com.ironman.book.entity.UserEntity;
import com.ironman.book.exception.DataNotFoundException;
import com.ironman.book.mapper.UserMapper;
import com.ironman.book.repository.UserRepository;
import com.ironman.book.service.UserService;
import com.ironman.book.util.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


// Lombok annotation to generate a constructor with required arguments
@RequiredArgsConstructor

// Spring Stereotype
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AppProperties appProperties;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public UserSecurityResponse create(UserCreateRequest userCreateRequest) {
        UserEntity userEntity = userMapper.toEntity(userCreateRequest);
        userEntity.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        userEntity.setProfileId(appProperties.getProfileDefault());
        userEntity.setStatus(StatusEnum.ENABLED.getValue());

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return userMapper.toSecurityResponse(savedUserEntity);
    }

    @Override
    public UserSecurityResponse login(LoginRequest loginRequest) {
        UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new DataNotFoundException("User not found with email: " + loginRequest.getEmail()));


        if(!passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
            throw new DataNotFoundException("Invalid password");
        }

        if(userEntity.getStatus().equals(StatusEnum.DISABLED.getValue())) {
            throw new DataNotFoundException("User is disabled");
        }

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userEntity.getProfile().getName());
        UserDetails userDetails = new User(userEntity.getEmail(), userEntity.getPassword(), List.of(grantedAuthority));

        UserSecurityResponse userSecurityResponse = userMapper.toSecurityResponse(userEntity);
        userSecurityResponse.setAccessToken(jwtService.generateToken(userDetails));

        return userSecurityResponse;
    }
}
