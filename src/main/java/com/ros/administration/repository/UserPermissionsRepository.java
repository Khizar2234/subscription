package com.ros.administration.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ros.administration.model.account.UserPermission;

@Repository
public interface UserPermissionsRepository extends JpaRepository<UserPermission, UUID> {

	@Query("select u from UserPermission u where u.id = :id")
	UserPermission findByUIIDId(@Param("id") UUID id);
	
	@Transactional
	@Modifying
	@Query(value="delete from user_permission u where u.feature_code is null and u.subscription_code is null",nativeQuery = true)
	void removeNullPermissions();

}