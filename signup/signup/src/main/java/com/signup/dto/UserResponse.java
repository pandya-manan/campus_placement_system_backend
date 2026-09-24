package com.signup.dto;

import com.signup.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Role role;
}