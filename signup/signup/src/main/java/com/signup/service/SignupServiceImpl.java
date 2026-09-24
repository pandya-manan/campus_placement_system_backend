package com.signup.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.signup.dto.SignupRequest;
import com.signup.dto.UserResponse;
import com.signup.entity.AppUser;
import com.signup.exception.EmailAlreadyExistsException;
import com.signup.exception.PhoneNumberAlreadyExistsException;
import com.signup.repository.SignupRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SignupServiceImpl implements SignupService {

	private final SignupRepository signupRepo;
	private final BCryptPasswordEncoder bcryptPasswordEncoder;

	public SignupServiceImpl(SignupRepository signUpRepo, BCryptPasswordEncoder bcryptPasswordEncoder) {
		this.signupRepo = signUpRepo;
		this.bcryptPasswordEncoder = bcryptPasswordEncoder;
	}

	@Override
	public UserResponse registerUser(SignupRequest request) {

		if (signupRepo.existsByEmail(request.getEmail())) {
			log.warn("Duplicate email attempted: {}", request.getEmail());
			throw new EmailAlreadyExistsException("Email already exists");
		}

		if (signupRepo.existsByPhoneNumber(request.getPhoneNumber())) {
			log.warn("Duplicate phone number attempted: {}", request.getPhoneNumber());
			throw new PhoneNumberAlreadyExistsException("Phone number already exists");
		}

		AppUser appUser = new AppUser();

		appUser.setFirstName(request.getFirstName());
		appUser.setLastName(request.getLastName());
		appUser.setPhoneNumber(request.getPhoneNumber());
		appUser.setEmail(request.getEmail());
		appUser.setPassword(bcryptPasswordEncoder.encode(request.getPassword()));
		appUser.setRole(request.getRole());

		AppUser savedUser = signupRepo.save(appUser);

		log.info("User registered successfully. User ID: {}, Email: {}", savedUser.getUserId(), savedUser.getEmail());

		return UserResponse.builder().userId(savedUser.getUserId()).firstName(savedUser.getFirstName())
				.lastName(savedUser.getLastName()).phoneNumber(savedUser.getPhoneNumber()).email(savedUser.getEmail())
				.role(savedUser.getRole()).build();
	}

}
