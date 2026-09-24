package com.signup.service;

import com.signup.dto.SignupRequest;
import com.signup.dto.UserResponse;
import com.signup.exception.EmailAlreadyExistsException;
import com.signup.exception.PhoneNumberAlreadyExistsException;

public interface SignupService {

	UserResponse registerUser(SignupRequest request) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException;
}
