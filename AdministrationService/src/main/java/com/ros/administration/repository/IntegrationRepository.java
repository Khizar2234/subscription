package com.ros.administration.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ros.administration.model.Integration;

@Repository
public interface IntegrationRepository extends JpaRepository<Integration, UUID> {
	
	@Query(value="select * from integration where integration_name is not null",nativeQuery = true)
	List<Integration> findAllIntegrations();


}
