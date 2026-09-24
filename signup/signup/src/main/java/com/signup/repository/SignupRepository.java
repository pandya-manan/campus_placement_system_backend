package com.signup.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.signup.entity.AppUser;

@Repository
public interface SignupRepository extends CrudRepository<AppUser, Long> {

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);
}
