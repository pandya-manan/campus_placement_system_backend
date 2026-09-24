package com.signup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.signup.dto.SignupRequest;
import com.signup.dto.UserResponse;
import com.signup.exception.EmailAlreadyExistsException;
import com.signup.exception.PhoneNumberAlreadyExistsException;
import com.signup.service.SignupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final SignupService signupService;

    public UserController(SignupService signupService) {
        this.signupService = signupService;
    }
    
    @Operation(
            summary = "Register a new user",
            description = "Creates a new ADMIN, STUDENT or PLACEMENT_CELL user."
    )
	@ApiResponse(responseCode = "201", description = "User registered successfully")
    @ApiResponse(responseCode = "409", description = "Email or phone already exists")
    @PostMapping
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody SignupRequest request) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {

        UserResponse response = signupService.registerUser(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}