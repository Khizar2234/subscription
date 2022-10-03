package com.ros.administration.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.ros.administration.controller.dto.AddressDto;
import com.ros.administration.controller.dto.account.ClientDto;
import com.ros.administration.controller.dto.account.ClientVatInfoDto;
import com.ros.administration.controller.dto.configuration.ShiftConfigurationDto;
import com.ros.administration.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.controller.dto.RestaurantIntegrationDto;
import com.ros.administration.exceptions.IntegrationNotFoundException;
//RestaurantOneSolution@dev.azure.com/RestaurantOneSolution/RestaurantOneSolution/_git/ROS_ADMINISTRATION-SERVICE
import com.ros.administration.exceptions.RestaurantAlreadyExistsException;
import com.ros.administration.exceptions.RestaurantNotFoundException;
import com.ros.administration.exceptions.UserNotFoundException;
import com.ros.administration.exceptions.UserRestaurantAlreadyExistsException;
import com.ros.administration.exceptions.UserRestaurantDeletionException;
import com.ros.administration.model.Address;
import com.ros.administration.model.Country;
import com.ros.administration.model.Integration;
import com.ros.administration.model.Restaurant;
import com.ros.administration.model.RestaurantIntegration;
import com.ros.administration.model.account.Client;
import com.ros.administration.model.account.ClientVatInfo;
import com.ros.administration.model.account.ContactInfo;
import com.ros.administration.model.account.PrimaryContact;
import com.ros.administration.model.account.User;
import com.ros.administration.model.enums.EAddress;
import com.ros.administration.repository.AddressRepository;
import com.ros.administration.repository.ClientContactInfoRepository;
import com.ros.administration.repository.ClientRepository;
import com.ros.administration.repository.ClientVatInfoRepository;
import com.ros.administration.repository.CountryRepository;
import com.ros.administration.repository.IntegrationRepository;
import com.ros.administration.repository.PrimaryContactRepository;
import com.ros.administration.repository.RestaurantIntegrationRepository;
import com.ros.administration.repository.RestaurantRepository;
import com.ros.administration.repository.UserRepository;
import com.ros.administration.service.RestaurantService;
import com.ros.administration.service.UserService;
import com.ros.administration.util.Properties;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RestaurantIntegrationRepository restaurantIntegrationRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PrimaryContactRepository primaryContactRepository;

	@Autowired
	private ClientContactInfoRepository clientContactInfoRepository;

	@Autowired
	private ClientVatInfoRepository clientVatInfoRepository;

	@Autowired
	private IntegrationRepository integrationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RestaurantMapper restaurantMapper;

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	private PrimaryContactMapper primaryContactMapper;

	@Autowired
	private ContactInfoMapper contactInfoMapper;

	@Autowired
	private ClientMapper clientMapper;

	@Autowired
	private ClientVatInfoMapper clientVatInfoMapper;

	@Autowired
	private IntegrationMapper integrationMapper;

	@Transactional
	@Override
	public RestaurantDto addRestaurant(RestaurantDto restaurant) throws RestaurantAlreadyExistsException {

		Country postalAddressCountry = null;
		Country physicalAddressCountry =null;
		Address physicalAddress = null;
		Address postalAddress = null;
		PrimaryContact primaryContact = null;
		if(restaurant.getPostalAddress()!=null && restaurant.getPostalAddress().getCountry() != null &&restaurant.getPostalAddress().getCountry().getId()!=null) {
			postalAddressCountry = countryRepository.findByUUID(restaurant.getPostalAddress().getCountry().getId());
			postalAddress = addressMapper.convertToAddress(restaurant.getPostalAddress());
			postalAddress.setCountry(postalAddressCountry);
			System.out.println(postalAddress.getCountry());
			postalAddress.setAddressName(EAddress.POSTAL);
		}
		if(restaurant.getPhysicalAddress()!=null && restaurant.getPhysicalAddress().getCountry()!=null && restaurant.getPhysicalAddress().getCountry().getId()!=null) {
			physicalAddressCountry = countryRepository
					.findByUUID(restaurant.getPhysicalAddress().getCountry().getId());
			physicalAddress = addressMapper.convertToAddress(restaurant.getPhysicalAddress());
			physicalAddress.setCountry(physicalAddressCountry);
			physicalAddress.setAddressName(EAddress.PHYSICAL);
		}

		if(restaurant.getPrimaryContact() !=null) {
			primaryContact = primaryContactMapper.convertToPrimaryContact(restaurant.getPrimaryContact());
		}

//		ContactInfo contactInfo = contactInfoMapper.convertToContactInfo(restaurant.getContactInformation());

		Country clientVatCountry = countryRepository.findByUUID(restaurant.getVatCountryId());

		ClientVatInfo vatInfo = new ClientVatInfo();
//		vatInfo.setId(clientVatCountry.getId());
		vatInfo.setCountry(postalAddressCountry);
		vatInfo.setTaxRegistrationNo(restaurant.getTaxRegistrationNo());

//		System.out.println(vatInfo);
		Client client = clientRepository.findByUUID(restaurant.getClientId());


//		addressRepository.save(postalAddress);
//		addressRepository.save(physicalAddress);
//		primaryContactRepository.save(primaryContact);
//		clientContactInfoRepository.save(contactInfo);
		clientVatInfoRepository.save(vatInfo);

		Restaurant newRestaurant = restaurantMapper.convertToRestaurant(restaurant);




		newRestaurant.addMetaData(newRestaurant.getName());
		newRestaurant.setPostalAddress(postalAddress);
		newRestaurant.setPhysicalAddress(physicalAddress);
		newRestaurant.setPrimaryContact(primaryContact);
		newRestaurant.setVatEditableCountryId(restaurant.getPostalId());
		newRestaurant.setVatCountryCode(restaurant.getPostalTaxCode());
		newRestaurant.setVatCountryName(restaurant.getPostalAddressCountry());
//		newRestaurant.setContactInformation(contactInfo);
		newRestaurant.setVatInfo(vatInfo);
		newRestaurant.setClient(client);
		newRestaurant = restaurantRepository.save(newRestaurant);
		addNewRestaurantIntegrations(newRestaurant.getId());
		if(newRestaurant.getClient()!=null && newRestaurant.getClient().getAccount()!=null
				&& newRestaurant.getClient().getAccount().getAccountEmail()!=null) {
			addRestaurantToDefaultUser(newRestaurant.getClient().getAccount().getAccountEmail(),newRestaurant.getId());
		}
		try {
			userService.addRestaurantToSuperAdmins(newRestaurant.getId());
		}catch(UserNotFoundException e) {
			System.out.println("Faced issues in adding restaurant to user");
		}
		return restaurantMapper.convertToRestaurantDto(newRestaurant);
	}


	private void addRestaurantToDefaultUser(String accountEmail,UUID restaurantId) {
		User user = userRepository.findByUsername(accountEmail);
		restaurantRepository.addRestaurantToUser(user.getId(), restaurantId);

	}

	@Transactional
	private void updateRestaurantIntegrations(UUID restaurantId) {
		List<Integration> integrations = integrationRepository.findAllIntegrations();
		Restaurant restaurant = restaurantRepository.findByUUID(restaurantId);
		List<RestaurantIntegration> restaurantIntegrations = new ArrayList();
		if (restaurant.getRestaurantIntegrations() != null && restaurant.getRestaurantIntegrations().size() != 0) {
			restaurantIntegrations = restaurant.getRestaurantIntegrations();
			for (Integration integration : integrations) {
				if (!restaurantIntegrationRepository.checkifIntegrationExists(integration.getId(), restaurantId)) {
					restaurantIntegrations.add(CreateRestaurantIntegrationFromIntegration(integration));
				}
			}
			restaurant.setRestaurantIntegrations(restaurantIntegrations);
			restaurantIntegrationRepository.saveAll(restaurantIntegrations);
		} else {
			addNewRestaurantIntegrations(restaurantId);
		}

	}

	private void addNewRestaurantIntegrations(UUID restaurantId) {
		// TODO Auto-generated method stub
		List<Integration> integrations = integrationRepository.findAllIntegrations();
		Restaurant restaurant = restaurantRepository.findByUUID(restaurantId);
		List<RestaurantIntegration> restaurantIntegrations = new ArrayList();
		for (Integration integration : integrations) {
			restaurantIntegrations.add(CreateRestaurantIntegrationFromIntegration(integration));
		}
		restaurant.setRestaurantIntegrations(restaurantIntegrations);
		restaurantIntegrationRepository.saveAll(restaurantIntegrations);
	}

	@Transactional
	private RestaurantIntegration CreateRestaurantIntegrationFromIntegration(Integration integration) {
		RestaurantIntegration restaurantIntegration = new RestaurantIntegration();
		restaurantIntegration.setId(UUID.randomUUID());
		restaurantIntegration.setIntegration(integration);
		restaurantIntegration.setIntegrationStatus(false);
		restaurantIntegration.setIntegrationCredentials("");
		restaurantIntegration.addMetaData();
		return restaurantIntegrationRepository.save(restaurantIntegration);
	}

	@Override
	public RestaurantDto findByUUID(UUID restaurantUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteRestaurant(UUID restaurantUUID) throws RestaurantNotFoundException {

		Restaurant restaurant = restaurantRepository.findByUUID(restaurantUUID);

		restaurantRepository.delete(restaurant);

		return "Restaurant Deleted Successfully";
	}

	@Override
	public RestaurantIntegrationDto editIntegrationStatus(UUID id, boolean integrationStatus,String integrationCredentials)
			throws IntegrationNotFoundException {

		RestaurantIntegration retRestaurantIntegration = restaurantIntegrationRepository.getOne(id);
		retRestaurantIntegration.setIntegrationStatus(integrationStatus);
		retRestaurantIntegration.setIntegrationCredentials(integrationCredentials);
		retRestaurantIntegration = restaurantIntegrationRepository.save(retRestaurantIntegration);
		return integrationMapper.convertToRestaurantIntegrationDto(retRestaurantIntegration);
	}

	@Override
		public RestaurantDto editRestaurant(RestaurantDto restaurantDto) throws RestaurantNotFoundException {
//		Restaurant newRestaurant = restaurantRepository.findByUUID(restaurantDto.getId());
		Restaurant restaurant =  restaurantRepository.findByUUID(restaurantDto.getId());

//		restaurantRepository.delete(newRestaurant);

		System.out.println(restaurant.getClient().getId());

		if (restaurant.getClient() != null && restaurantDto.getClientId() != null
				&& !restaurant.getClient().getId().equals(restaurantDto.getClientId())) {
			Client client = clientRepository.findByUUID(restaurantDto.getClientId());
			restaurant.setClient(client);
		}
		if (restaurant.getVatInfo().getCountry() != null && restaurant.getVatInfo().getCountry().getId() != null) {
			if (!restaurant.getVatInfo().getCountry().getId().equals(restaurantDto.getVatCountryId())) {
				Country clientVatCountry = countryRepository.findByUUID(restaurantDto.getVatCountryId());

				restaurant.getVatInfo().setCountry(clientVatCountry);
				restaurant.getVatInfo().setTaxRegistrationNo(restaurantDto.getTaxRegistrationNo());
			}
			if (restaurant.getVatInfo().getCountry().getId().equals(restaurantDto.getVatCountryId())) {
				restaurant.getVatInfo().setTaxRegistrationNo(restaurantDto.getTaxRegistrationNo());
			}
		}
		updateRestaurantIntegrations(restaurant.getId());

		System.out.println(restaurantDto.getPostalAddressCountry());

		restaurantDto.getPhysicalAddress().getCountry().setCountryName(restaurantDto.getPhysicalAddressCountry());
		restaurantDto.getPostalAddress().getCountry().setCountryName(restaurantDto.getPostalAddressCountry());

		restaurantDto.setVatEditableCountryId((restaurantDto.getPostalId()));
		restaurantDto.setVatCountryName(restaurantDto.getPostalAddressCountry());
		restaurantDto.setVatCountryCode(restaurantDto.getPostalTaxCode());

		System.out.println(restaurant.getVatCountryName());

		System.out.println("here");


		restaurantMapper.updateRestaurant(restaurantDto, restaurant);
		return restaurantMapper.convertToRestaurantDto(restaurantRepository.save(restaurant));
//		return null;
		}


	@Override
	public RestaurantDto addRestaurantIntegrations(RestaurantDto restaurantDto) {
		Restaurant restaurant;
		Optional<Restaurant> optRestaurant = restaurantRepository.findById(restaurantDto.getId());
		if (optRestaurant.isPresent()) {
			restaurant = restaurantRepository.save(restaurantMapper.convertToRestaurant(restaurantDto));

		} else {
			restaurant = optRestaurant.get();
//			restaurant.setIntegrations(integrationMapper.convertToIntegrationList(restaurantDto.getIntegrations())); 
			restaurant = restaurantRepository.save(restaurant);
		}

		return restaurantMapper.convertToRestaurantDto(restaurant);

	}

	@Override
	public List<RestaurantIntegrationDto> getRestaurantIntegrations(UUID restaurantId)
			throws RestaurantNotFoundException {
		Restaurant restaurant = restaurantRepository.findByUUID(restaurantId);
		return integrationMapper.convertToRestaurantIntegrationDtoList(restaurant.getRestaurantIntegrations());

	}

	@Override
	public List<RestaurantDto> getRestaurantDetails(UUID clientId) {
		// TODO Auto-generated method stub
		List<Restaurant> restaurant = restaurantRepository.findByClientUUID(clientId);
		return restaurantMapper.convertToRestaurantDtoList(restaurant);
	}

	@Override
	public List<RestaurantDto> getRestaurantDetails() {
		// TODO Auto-generated method stub
		List<Restaurant> restaurant = restaurantRepository.getAllRestaurants();
		return restaurantMapper.convertToRestaurantDtoList(restaurant);
	}

	@Override
	public RestaurantDto getRestaurantById(UUID id) {
		// TODO Auto-generated method stub
		Restaurant restaurant = restaurantRepository.findByUUID(id);
		return restaurantMapper.convertToRestaurantDto(restaurant);
	}

	@Transactional
	@Override
	public String addRestaurantToUser(UUID userId, UUID restaurantId)
			throws UserRestaurantAlreadyExistsException, RestaurantNotFoundException {
		// TODO Auto-generated method stub
		if (restaurantRepository.findByUUID(restaurantId) != null) {
			if (!restaurantRepository.checkIfUserRestaurantExists(userId, restaurantId)) {

				restaurantRepository.addRestaurantToUser(userId, restaurantId);
				return Properties.userRestaurantsSuccessful;

			} else {
				throw new UserRestaurantAlreadyExistsException(Properties.userRestaurantAlreadyExists + " : "
						+ restaurantId + " and user id : " + userId + "\n");
			}
		} else {
			throw new RestaurantNotFoundException(Properties.restaurantNotFound + " : " + restaurantId + "\n");
		}
	}

	@Transactional
	@Override
	public String addRestaurantsToUser(UUID userId, List<UUID> restaurantIds) {
		String errorDefault = "Faced errors in following : \n";

		String error = new String(errorDefault);

		for (UUID restaurantId : restaurantIds) {
			try {
				this.addRestaurantToUser(userId, restaurantId);
			} catch (UserRestaurantAlreadyExistsException e) {
				System.out.println(e.getMessage());
				error = error + e.getMessage();
			} catch (RestaurantNotFoundException e) {
				System.out.println(e.getMessage());
				error = error + e.getMessage();
			}
		}

		if (error.equalsIgnoreCase(errorDefault)) {
			return Properties.userRestaurantsSuccessful;
		} else {
			return error;
		}
	}

	@Override
	public String updateUserRestaurant(UUID userId, UUID restaurantId)
			throws UserRestaurantAlreadyExistsException, RestaurantNotFoundException {
		// TODO Auto-generated method stub
		if (restaurantRepository.findByUUID(restaurantId) != null) {
			if (!restaurantRepository.checkIfUserRestaurantExists(userId, restaurantId)) {

				restaurantRepository.addRestaurantToUser(userId, restaurantId);
				return Properties.userRestaurantsSuccessful;

			} else {
				throw new UserRestaurantAlreadyExistsException(Properties.userRestaurantAlreadyExists + " : "
						+ restaurantId + " and user id : " + userId + "\n");
			}
		} else {
			throw new RestaurantNotFoundException(Properties.restaurantNotFound + " : " + restaurantId + "\n");
		}
	}

	@Override
	public String updateUserRestaurantConnections(UUID userId, List<UUID> restaurantIds){
		String errorDefault = "Faced errors in following : \n";
		String error = new String(errorDefault);

		User user = userRepository.getById(userId);
		Set<Restaurant> restaurants = new HashSet();
		if(user.getRestaurants()!=null) {
			restaurants=user.getRestaurants();
			try {
				restaurants= deleteUserRestaurants(userId,restaurants,restaurantIds);
			}catch(UserRestaurantDeletionException e) {
				error = error + Properties.userRestaurantDeletionFailed;
			}

		}
		for (UUID restaurantId : restaurantIds) {
			if(restaurants.stream().anyMatch(restaurant -> restaurant.getId().equals(restaurantId))) {
				System.out.println("id matches existing object");
			}
			else {
				try {
					this.addRestaurantToUser(userId, restaurantId);
				} catch (UserRestaurantAlreadyExistsException e) {

				} catch (RestaurantNotFoundException e) {
					System.out.println(e.getMessage());
					error = error + e.getMessage();
				}
			}
		}

		if (error.equalsIgnoreCase(errorDefault)) {
			return Properties.userRestaurantsSuccessful;
		} else {
			return error;
		}
	}

	private Set<Restaurant> deleteUserRestaurants(UUID userId, Set<Restaurant> restaurants, List<UUID> restaurantIds) throws UserRestaurantDeletionException{
		for(Restaurant restaurant : restaurants) {
			if(restaurantIds.stream().noneMatch(restaurantId -> restaurant.getId().equals(restaurantId))) {
				System.out.println("removing "+restaurant.getName());
				restaurants.remove(restaurant);
				restaurantRepository.deleteUserRestaurant(userId,restaurant.getId());
			}
		}
		return restaurants;
	}

	@Override
	public List<RestaurantDto> getAllRestaurants(int limit, int pageNo) throws RestaurantNotFoundException {
		List<Restaurant> restaurant = restaurantRepository.findPagewiseRestaurants(limit,pageNo*limit);
		return restaurantMapper.convertToRestaurantDtoList(restaurant);
	}


	@Override
	public String setRestaurantPin(UUID clientId, UUID restaurantId, String pin) throws Exception {


		List<Restaurant> restaurants = restaurantRepository.findByClientUUID(clientId);
		
		boolean duplicatePinCheck = false;
		
		for(Restaurant restaurant: restaurants) {
			
			if(restaurant.getRestaurantPinHash() != null && ! duplicatePinCheck) {
				
				if (BCrypt.checkpw(pin, restaurant.getRestaurantPinHash()))
					duplicatePinCheck = true;
				
			}
			
		}
		
		if(duplicatePinCheck)
			throw new Exception("Please select a unique Pin for the Restaurant!");
		
		else {
			
			Restaurant res = restaurantRepository.getById(restaurantId);
			
			if(res == null)
				throw new Exception("Restaurant with Restaurant id: "+ restaurantId + " doesnot exists!");

			
			res.setRestaurantPinHash(BCrypt.hashpw(pin, BCrypt.gensalt()));
			
			res.setTabletValidation(false);
			
			restaurantRepository.save(res);
			
			return "Pin Set successfully for Restaurant: "+ res.getName();
		}
		

	}


	@Override
	public String editRestaurantPin(UUID clientId, UUID restaurantId, String oldPin, String newPin) throws Exception {
		Restaurant res = restaurantRepository.getById(restaurantId);
		
		if(res == null)
			throw new Exception("Restaurant with Restaurant id: "+ restaurantId + " doesnot exists!");
		
		if(res.getRestaurantPinHash() == null)
			throw new Exception("Restaurant Pin for Restaurant id: "+ restaurantId + " doesnot exists!");
		
		if(res.getRestaurantPinHash() != null && ! BCrypt.checkpw(oldPin, res.getRestaurantPinHash()))
			throw new Exception("Old Pin doesnot Match!!");
		
		else {
			
			List<Restaurant> restaurants = restaurantRepository.findByClientUUID(clientId);
			
			boolean duplicatePinCheck = false;
			
			for(Restaurant restaurant: restaurants) {
				
				if(restaurant.getRestaurantPinHash() != null && ! duplicatePinCheck) {
					
					if (BCrypt.checkpw(newPin, restaurant.getRestaurantPinHash()))
						duplicatePinCheck = true;
					
				}
				
			}
			
			if(duplicatePinCheck)
				throw new Exception("Please select a unique Pin for the Restaurant!");
			
			else {
			
			res.setRestaurantPinHash(BCrypt.hashpw(newPin, BCrypt.gensalt()));
			
			res.setTabletValidation(false);
			
			restaurantRepository.save(res);
			
			return "Pin updated successfully for Restaurant: "+ res.getName();
			
			}
		}
		
	}


	@Override
	public String verifyRestaurantPin(UUID restaurantId, String pin) throws Exception {
		
		Restaurant res = restaurantRepository.getById(restaurantId);
		
		if(res == null)
			throw new Exception("Restaurant with Restaurant id: "+ restaurantId + " doesnot exists!");
		
		if(res.getRestaurantPinHash() == null)
			throw new Exception("Restaurant Pin for Restaurant: "+ res.getName() + " doesnot exists!");
		
		if(BCrypt.checkpw(pin, res.getRestaurantPinHash())) {
			
			res.setTabletValidation(true);
			
			restaurantRepository.save(res);
			
			return  "Restaurant Validated Successfully";
		}
		else
			return  "Restaurant Validated Failed, Please enter the correct Pin for Restaurant: "+ res.getName();

	}

}
