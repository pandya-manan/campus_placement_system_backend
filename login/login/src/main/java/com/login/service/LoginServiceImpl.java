package com.login.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.dto.LoginRequest;
import com.login.dto.LoginResponse;
import com.login.entity.AppUser;
import com.login.exception.InvalidCredentialsException;
import com.login.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

	private final UserRepository userRepo;
	private final BCryptPasswordEncoder encoder;
	private final JwtService jwtService;

	public LoginServiceImpl(UserRepository userRepo, BCryptPasswordEncoder encoder,JwtService jwtService) {
		super();
		this.userRepo = userRepo;
		this.encoder = encoder;
		this.jwtService = jwtService;
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		AppUser appUser = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(()->new InvalidCredentialsException("Invalid email or password"));
		if(!encoder.matches(loginRequest.getPassword(), appUser.getPassword()))
		{
			log.warn("Failed login attempt for the user: {}",loginRequest.getEmail());
			throw new InvalidCredentialsException("Invalid email or password");
		}
		log.info("User logged in successfully: {}",loginRequest.getEmail());
		String accessToken = jwtService.generateToken(appUser);
		return LoginResponse.builder().userId(appUser.getUserId())
				.firstName(appUser.getFirstName())
				.lastName(appUser.getLastName())
				.email(appUser.getEmail())
				.role(appUser.getRole())
				.accessToken(accessToken)
				.refreshToken(null)
				.build();
		
	}

}
