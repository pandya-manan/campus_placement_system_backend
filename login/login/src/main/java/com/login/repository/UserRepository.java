package com.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.entity.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long>{
	
	Optional<AppUser> findByEmail(String email);

}
