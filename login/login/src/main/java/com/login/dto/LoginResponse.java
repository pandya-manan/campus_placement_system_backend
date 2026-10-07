package com.login.dto;

import com.login.entity.Role;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {

	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	private String accessToken;
	private String refreshToken;
}
