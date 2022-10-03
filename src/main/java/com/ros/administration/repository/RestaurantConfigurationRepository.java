package com.ros.administration.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.administration.model.account.Client;
import com.ros.administration.model.configuration.RestaurantConfiguration;

@Repository
//
public interface RestaurantConfigurationRepository extends JpaRepository<RestaurantConfiguration, UUID>{

//	@Query("SELECT r FROM RestaurantConfiguration r WHERE r.restaurant.id = :id")
//	public RestaurantConfiguration findByResaurantId(@Param("id") UUID id);
	
}
