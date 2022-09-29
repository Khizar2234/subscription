package com.ros.administration.repository;


import com.ros.administration.model.Restaurant;
import com.ros.administration.model.configuration.PDQCard;
import com.ros.administration.model.configuration.PDQMachine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface PDQCardRepository extends JpaRepository<PDQCard, UUID> {
	@Query("select distinct c.name from PDQCard c")
	List<String> findUniqueCards();

}
