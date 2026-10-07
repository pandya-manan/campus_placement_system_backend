package com.login.service;

import com.login.dto.LoginRequest;
import com.login.dto.LoginResponse;

public interface LoginService {

	LoginResponse login(LoginRequest loginRequest);
}
