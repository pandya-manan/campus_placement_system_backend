package com.signup.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.signup.dto.SignupRequest;
import com.signup.dto.UserResponse;
import com.signup.entity.AppUser;
import com.signup.entity.Role;
import com.signup.exception.EmailAlreadyExistsException;
import com.signup.exception.PhoneNumberAlreadyExistsException;
import com.signup.repository.SignupRepository;

public class SignupServiceTestImpl {

	@Mock
	private SignupRepository signupRepository;

	@Mock
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@InjectMocks
	private SignupServiceImpl signupServiceImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	private SignupRequest getRequest() {
		SignupRequest request = new SignupRequest();
		request.setFirstName("Manan");
		request.setLastName("Pandya");
		request.setEmail("manan@gmail.com");
		request.setPhoneNumber("9876543210");
		request.setPassword("Password@123");
		request.setRole(Role.STUDENT);
		return request;
	}

	@Test
	void shouldRegisterUserSuccessfully() {

		SignupRequest request = getRequest();

		when(signupRepository.existsByEmail(request.getEmail())).thenReturn(false);
		when(signupRepository.existsByPhoneNumber(request.getPhoneNumber())).thenReturn(false);
		when(bcryptPasswordEncoder.encode(request.getPassword())).thenReturn("hashedPassword");

		AppUser saved = new AppUser();
		saved.setUserId(1L);
		saved.setFirstName(request.getFirstName());
		saved.setLastName(request.getLastName());
		saved.setEmail(request.getEmail());
		saved.setPhoneNumber(request.getPhoneNumber());
		saved.setPassword("hashedPassword");
		saved.setRole(Role.STUDENT);

		when(signupRepository.save(any(AppUser.class))).thenReturn(saved);

		UserResponse response = signupServiceImpl.registerUser(request);

		assertEquals(1L, response.getUserId());
		assertEquals("Manan", response.getFirstName());
		assertEquals(Role.STUDENT, response.getRole());

		verify(bcryptPasswordEncoder).encode("Password@123");
		verify(signupRepository).save(any(AppUser.class));
	}

	@Test
	void shouldThrowExceptionWhenEmailExists() {

		SignupRequest request = getRequest();

		when(signupRepository.existsByEmail(request.getEmail())).thenReturn(true);

		assertThrows(EmailAlreadyExistsException.class, () -> signupServiceImpl.registerUser(request));

		verify(signupRepository, never()).save(any());
	}

	@Test
	void shouldThrowExceptionWhenPhoneExists() {

		SignupRequest request = getRequest();

		when(signupRepository.existsByEmail(request.getEmail())).thenReturn(false);
		when(signupRepository.existsByPhoneNumber(request.getPhoneNumber())).thenReturn(true);

		assertThrows(PhoneNumberAlreadyExistsException.class, () -> signupServiceImpl.registerUser(request));

		verify(signupRepository, never()).save(any());
	}
}
