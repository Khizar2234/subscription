package com.ros.administration.repository;

import com.ros.administration.model.account.Account;
import com.ros.administration.model.account.Client;
import com.ros.administration.model.enums.EStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
	
	//My CLient Repository

	@Query("select u from Client u where u.id = :id")
	Client findByUUID(@Param("id") UUID id);

	@Query("select c from Client c")
	Client viewAllClients();

	@Query("select c from Client c where c.name = :clientName")
	Client findByClientName(@Param("clientName") String clientName);

	@Query("select c from Client c where c.primaryContact.primaryContactEmail = :primaryContactEmail")
	public Client findByPrimaryEmail(@Param("primaryContactEmail")String primaryContactEmail);

	@Query("select c from Client c where c.account.id = :id")
	Client findClientByAccountId(@Param("id")UUID id);




}
