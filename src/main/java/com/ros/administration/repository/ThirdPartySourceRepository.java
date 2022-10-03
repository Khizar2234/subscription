package com.ros.administration.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ros.administration.model.configuration.ThirdPartyCategory;
import com.ros.administration.model.configuration.ThirdPartySource;

@Repository
public interface ThirdPartySourceRepository extends JpaRepository<ThirdPartySource, UUID>{

	@Query("select distinct t.title from ThirdPartySource t")
	List<String> findUniqueThirdParties();
	

}
