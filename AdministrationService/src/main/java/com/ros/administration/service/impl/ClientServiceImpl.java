package com.ros.administration.service.impl;

import com.ros.administration.controller.dto.account.ClientAccountSubscriptionDto;
import com.ros.administration.controller.dto.account.ClientAddDto;
import com.ros.administration.controller.dto.account.ClientDetailedInfoDto;
import com.ros.administration.controller.dto.account.ClientDto;
import com.ros.administration.exceptions.*;
import com.ros.administration.mapper.AccountMapper;
import com.ros.administration.mapper.AddressMapper;
import com.ros.administration.mapper.ClientMapper;
import com.ros.administration.mapper.PrimaryContactMapper;
import com.ros.administration.mapper.UserMapper;
import com.ros.administration.model.Address;
import com.ros.administration.model.Country;
import com.ros.administration.model.account.*;
import com.ros.administration.model.enums.EStatus;
import com.ros.administration.model.subscription.Subscription;
import com.ros.administration.repository.AccountRepository;
import com.ros.administration.repository.AccountSubscriptionRepository;
import com.ros.administration.repository.AddressRepository;
import com.ros.administration.repository.ClientRepository;
import com.ros.administration.repository.CountryRepository;
import com.ros.administration.repository.PrimaryContactRepository;
import com.ros.administration.repository.SubscriptionRepository;
import com.ros.administration.repository.UserRepository;
import com.ros.administration.service.ClientService;
import com.ros.administration.service.UserPermissionsService;
import com.ros.administration.service.UserService;
import com.ros.administration.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientReposistory;


	@Autowired
	UserPermissionsService userPermissionsService;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PrimaryContactRepository primaryContactRepository;

	// Service Implementation
	@Autowired
	private PrimaryContactMapper primaryContactMapper;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private ClientMapper clientMapper;

	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private AccountSubscriptionRepository accountSubscriptionRepository;

	@Override
	public ClientDto getClientDetailsById(UUID id) {
		Client client = clientReposistory.findByUUID(id);
		System.out.println(client.getPrimaryContact());
		return clientMapper.convertToClientDto(client);
	}

	@Override
	public ClientDetailedInfoDto getClientInfoDetails(UUID id) {
		Client client = clientReposistory.findByUUID(id);
//		System.out.println(client.getPrimaryContact());
		return clientMapper.convertToClientDetailedInfoDto(client);
	}

	@Override
	public ClientDto updateClient(ClientDto clientdto) {
		// TODO Auto-generated method stub\
		Client client = clientReposistory.findByUUID(clientdto.getId());

		if (client.getAddress().getCountry().getId() != null) {
			if (!client.getAddress().getCountry().getId().equals(clientdto.getAddress().getCountry().getId())) {
				Country country = countryRepository.findByUUID(clientdto.getAddress().getCountry().getId());

				client.getAddress().setCountry(country);
			}
		}

		clientMapper.updateClient(clientdto, client);

		return clientMapper.convertToClientDto(clientReposistory.save(client));
	}
 
	@Transactional
	@Override
	public ClientDto addClient(ClientAddDto clientAddDto) {
		// TODO Auto-generated method stub
		PrimaryContact primaryContact = primaryContactMapper.convertToPrimaryContact(clientAddDto.getPrimaryContact());
		Country clientVatCountry = countryRepository.findByUUID(clientAddDto.getAddress().getCountry().getId());
		Address address = addressMapper.convertToAddress(clientAddDto.getAddress());
		address.setCountry(clientVatCountry);

		addressRepository.save(address);

		Client newClient = clientMapper.convertToCLientAdd(clientAddDto);
		newClient.setPrimaryContact(primaryContact);
		newClient.setAddress(address);
		newClient = clientReposistory.save(newClient);
		Account account = addClientAccount(clientAddDto);
		Optional<Account> optAccount = accountRepository.findById(account.getId());
		if (optAccount.isPresent()) {
			newClient.setAccount(optAccount.get());
			newClient = clientReposistory.save(newClient);
		}
		return clientMapper.convertToClientDto(newClient);
	}

	@Transactional
	private Account addClientAccount(ClientAddDto clientAddDto) {
		Account account = new Account();
		account.setAccountEmail(clientAddDto.getPrimaryContact().getPrimaryContactEmail());
		account.setClientName(clientAddDto.getName());
		account.setAccountStatus(EStatus.ACTIVE);
		User user;
		String username = "";
		try {
			user = userService.saveClientAsUser(clientAddDto);
			account.setDefaultUser(user);
			account.addMetaData(clientAddDto.getPrimaryContact().getPrimaryContactEmail());
			username = user.getUsername();

		} catch (PrimaryContactNotFoundException e) {
			// TODO Auto-generated catch block
			account.addMetaData();
		}
		account = accountRepository.save(account);
		List<String> subscriptionCodes = new ArrayList<String>();
		subscriptionCodes.add("ERP_PRM");
		account.setAccountSubscriptions(createAccountSubscriptionsByCode(subscriptionCodes,clientAddDto.getName()));
		account = accountRepository.save(account);
		userService.saveAccountAndSubscriptionData(account.getAccountEmail(),"ERP_PRM",account.getId() );
		AccountSubscription clientAccountSubscription = accountSubscriptionRepository.getMasterAccountSubscription(account.getId());
		try {
			userPermissionsService.setAllUserPermissions(username,clientAccountSubscription.getSubscription().getSubscriptionCode());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		userRepository.saveUserAccountData(user.getId(),account.getId());
		return account;
	}


	@Override
	public List<AccountSubscription> createAccountSubscriptionsByCode(List<String> subscriptionCodes, String activatedBy) {
		List<AccountSubscription> accountSubscriptions = new ArrayList<>();
		
		for(String subscriptionCode:subscriptionCodes) {
		Optional<Subscription> subscription = subscriptionRepository.findBySubscriptionCode(subscriptionCode);
		
		if (subscription.isPresent()) {
			AccountSubscription accSubscription = new AccountSubscription(UUID.randomUUID(), subscription.get(),
					activatedBy, new Date(), new Date(), EStatus.ACTIVE);

			accountSubscriptions.add(accSubscription);
//			userRepository.saveUserAccountSubscriptionData(user.getId(),account.getAccountSubscriptions().get(0).getId());
//		account.setAccountSubscriptions(accSubscription);

//		List<AccountSubscription> accountSubscriptions = account.getAccountSubscriptions();

		}
		}
		return accountSubscriptions; 
	}

	@Override
	public List<ClientDetailedInfoDto> viewAllClients() {
		List<Client> clients = clientReposistory.findAll();
		// System.out.println(clients);
		List<ClientDetailedInfoDto> clientDtos = new ArrayList<ClientDetailedInfoDto>();

		for (int i = 0; i < clients.size(); i++) {
			ClientDetailedInfoDto c = clientMapper.convertToClientDetailedInfoDto(clients.get(i));

			clientDtos.add(c);

		}
		return clientDtos;
	}

	@Override
	public Client getClient(String clientName) throws ClientNotFoundException {
		Client client = clientReposistory.findByClientName(clientName);
		return client;
	}

	@Override
	public String deleteClient(UUID id) throws ClientNotFoundException {

		Client client = clientReposistory.findByUUID(id);

		clientReposistory.delete(client);

		return "Client Deleted Successfully";
	}

	@Override
	public ClientAccountSubscriptionDto getAccountSubscriptionsForClient(UUID clientId)
			throws AccountSubscriptionNotFoundException {
		
		Client client = clientReposistory.findByUUID(clientId);
		if (client!=null && client.getAccount()!=null) {
			return clientMapper.convertToAccountSubsciptionDto(client.getAccount());
		}
		else {
			throw new AccountSubscriptionNotFoundException(Properties.accountSubscriptionNotFound);
		}
	}

	@Override
	public ClientDetailedInfoDto editClientEstatus(UUID id, EStatus accountStatus)
			throws ClientAccountStatusNotFoundException {
		Client actStatus = clientReposistory.getOne(id);
		List<User> users = actStatus.getAccount().getUsers();
		actStatus.getAccount().setAccountStatus(accountStatus);
		for(User user : users){
			if (accountStatus.compareTo(EStatus.ACTIVE) == 0) {
				user.setUserStatus(EStatus.ACTIVE);
			}
			else if (accountStatus.compareTo(EStatus.INACTIVE) == 0){
				user.setUserStatus(EStatus.INACTIVE);
			}
		}
		actStatus = clientReposistory.save(actStatus);
		return clientMapper.convertToClientDetailedInfoDto(actStatus);

		}

	@Override
	public ClientDetailedInfoDto getClientDetailsByAccountId(UUID id) throws ClientNotFoundException {
		Client client = clientReposistory.findClientByAccountId(id);
		return clientMapper.convertToClientDetailedInfoDto(client);

	}

	@Override
	public ClientDetailedInfoDto viewClientByEmail(String primaryContactEmail) throws  ClientNotFoundException{
		Client client = clientReposistory.findByPrimaryEmail(primaryContactEmail);
				return clientMapper.convertToClientDetailedInfoDto(client);
	}

//	@Override
//	public String setClientPin(UUID clientId, String pin) throws Exception {
//	
//		if(! clientReposistory.existsById(clientId))
//			throw new Exception("Client with Client Id: "+ clientId + " doesnpt exists!");
//		
//		Client client = clientReposistory.findByUUID(clientId);
//		
//		String clientPinHash = BCrypt.hashpw(pin, BCrypt.gensalt());
//		
//		client.setClientPinHash(clientPinHash);
//		
//		clientReposistory.save(client);
//
//		return "Client Pin added Scuccessfully";
//	}
//
//	@Override
//	public String editClientPin(UUID clientId, String oldPin, String newPin) throws Exception {
//
//		if(! clientReposistory.existsById(clientId))
//			throw new Exception("Client with Client Id: "+ clientId + " doesnot exists!");
//		
//		Client client = clientReposistory.findByUUID(clientId);
//		
//		if(! (BCrypt.checkpw(oldPin, client.getClientPinHash())))
//			throw new Exception("Old Pin doesnot Match!");
//
//		
//		List<Client> clients = clientReposistory.findAll();
//		
//		boolean uniquePin = true;
//		
//		while(uniquePin) {
//			for(Client c: clients) {
//				
//				if (BCrypt.checkpw(newPin, c.getClientPinHash()))
//					uniquePin = false;
//					
//			}
//			
//		if(!uniquePin)	
//			throw new Exception("Please enter an Unique Pin!");
//
//		
//			
//		}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		return null;
//	}
//
//	@Override
//	public String verifyClientPin(UUID clientId, String pin) throws Exception {
//
//		if(! clientReposistory.existsById(clientId))
//			throw new Exception("Client with Client Id: "+ clientId + " doesnpt exists!");
//		
//		return null;
//	}

	
}
