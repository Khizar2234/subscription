package com.ros.administration.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ros.administration.model.account.Role;
import com.ros.administration.model.enums.ERole;


public interface RoleRepository extends JpaRepository<Role, UUID> {
	Optional<Role> findByName(ERole name);

	
}
