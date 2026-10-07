package com.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.dto.LoginRequest;
import com.login.dto.LoginResponse;
import com.login.service.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final LoginService loginService;

	public AuthController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}
	
	@Operation(
            summary = "Login an existing user",
            description = "This API will enable the user to login"
    )
	@ApiResponse(responseCode = "200", description = "Successful login")
    @ApiResponse(responseCode = "401", description = "Unauthorized Access, Access Denied, Invalid credentials")
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest loginRequest)
	{
		return new ResponseEntity<>(loginService.login(loginRequest),HttpStatus.OK);
	}
	
	@Operation(
			summary = "Test end point to check authentication",
			description = "This API will check if the user is authenticated",
			security = @SecurityRequirement(name = "Bearer Authentication")
	)
	@ApiResponse(responseCode = "200", description = "Returns the authentication name")
	@GetMapping("/me")
	public ResponseEntity<String> me(Authentication authentication)
	{
		return ResponseEntity.ok(authentication.getName());
	}
}
