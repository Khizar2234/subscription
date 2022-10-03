package com.ros.administration.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ros.administration.controller.dto.account.ClientAccountSubscriptionDto;
import com.ros.administration.controller.dto.account.ClientAddDto;
import com.ros.administration.controller.dto.account.ClientDetailedInfoDto;
import com.ros.administration.controller.dto.account.ClientDto;
import com.ros.administration.exceptions.AccountSubscriptionNotFoundException;
import com.ros.administration.exceptions.ClientAccountStatusNotFoundException;
import com.ros.administration.exceptions.ClientNotFoundException;
import com.ros.administration.model.account.AccountSubscription;
import com.ros.administration.model.account.Client;
import com.ros.administration.model.enums.EStatus;

@Service
public interface ClientService {


	//Service
	ClientDto addClient(ClientAddDto dto);

	ClientDto getClientDetailsById(UUID id);

	ClientDto updateClient(ClientDto clientdto);

	List<ClientDetailedInfoDto> viewAllClients();

	ClientDetailedInfoDto getClientInfoDetails(UUID id);

	Client getClient(String clientName) throws ClientNotFoundException;

	String deleteClient(UUID id) throws ClientNotFoundException;

	ClientAccountSubscriptionDto getAccountSubscriptionsForClient(UUID clientId) throws AccountSubscriptionNotFoundException;

	List<ClientAccountSubscriptionDto> getAllAccountSubscriptionsForClients()
			throws AccountSubscriptionNotFoundException;

	ClientDetailedInfoDto editClientEstatus(UUID id, EStatus accountStatus)throws ClientAccountStatusNotFoundException;

	ClientDetailedInfoDto getClientDetailsByAccountId(UUID id) throws ClientNotFoundException;

	List<AccountSubscription> createAccountSubscriptionsByCode(List<String> subscriptionCodes, String activatedBy);

	ClientDetailedInfoDto viewClientByEmail(String primaryContactEmail) throws ClientNotFoundException;





}
