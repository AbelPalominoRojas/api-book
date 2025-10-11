package com.ironman.book.service;

import com.ironman.book.dto.user.LoginRequest;
import com.ironman.book.dto.user.UserCreateRequest;
import com.ironman.book.dto.user.UserSecurityResponse;

public interface UserService {
    UserSecurityResponse create(UserCreateRequest userCreateRequest);

    UserSecurityResponse login(LoginRequest loginRequest);
}
