package com.ros.administration.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ros.administration.controller.dto.configuration.PDQMachineDto;
import com.ros.administration.model.configuration.PDQMachine;
import com.ros.administration.model.configuration.PDQType;
import com.ros.administration.model.configuration.PettyCashType;
@Repository
public interface CashnPdqRepository extends JpaRepository<PDQType, UUID>{

	@Query("select distinct p.name from PDQMachine p")
	List<String> findUniqueMachines();

	@Query("select distinct p.title from PettyCashType p")
	List<String> findUniquePettyCashs();
	
	@Query("select distinct c.name from PDQCard c")
	List<String> findUniqueCards();
}
