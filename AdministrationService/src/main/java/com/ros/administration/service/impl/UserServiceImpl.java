package com.ros.administration.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.controller.dto.account.ClientAddDto;
import com.ros.administration.controller.dto.account.ClientDto;
import com.ros.administration.controller.dto.account.user.AllUserPermissionsDto;
import com.ros.administration.controller.dto.account.user.DetailedUserInfoDto;
import com.ros.administration.controller.dto.account.user.UserAddDto;
import com.ros.administration.controller.dto.account.user.UserDetailsDto;
import com.ros.administration.controller.dto.account.user.UserDto;
import com.ros.administration.controller.dto.account.user.UserInfoDto;
import com.ros.administration.controller.dto.account.user.UserRestaurantDto;
import com.ros.administration.controller.dto.account.user.UserStatusDto;
import com.ros.administration.exceptions.PrimaryContactNotFoundException;
import com.ros.administration.exceptions.RestaurantNotFoundException;
import com.ros.administration.exceptions.UserAlreadyExistsException;
import com.ros.administration.exceptions.UserNotFoundException;
import com.ros.administration.exceptions.UserRestaurantAlreadyExistsException;
import com.ros.administration.mapper.ClientMapper;
import com.ros.administration.mapper.RestaurantMapper;
import com.ros.administration.mapper.UserMapper;
import com.ros.administration.model.Restaurant;
import com.ros.administration.model.account.Account;
import com.ros.administration.model.account.AccountSubscription;
import com.ros.administration.model.account.Client;
import com.ros.administration.model.account.User;
import com.ros.administration.model.account.UserProfile;
import com.ros.administration.model.enums.ERole;
import com.ros.administration.model.enums.EStatus;
import com.ros.administration.model.subscription.Subscription;
import com.ros.administration.repository.AccountRepository;
import com.ros.administration.repository.AccountSubscriptionRepository;
import com.ros.administration.repository.RestaurantRepository;
import com.ros.administration.repository.SubscriptionRepository;
import com.ros.administration.repository.UserRepository;
import com.ros.administration.service.ClientService;
import com.ros.administration.service.RestaurantService;
import com.ros.administration.service.UserPermissionsService;
import com.ros.administration.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountSubscriptionRepository accountSubscriptionRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UserPermissionsService userPermissionsService;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ClientMapper clientMapper;
	
	@Autowired
	private RestaurantMapper restaurantMapper;	

//	@Override
//	@Transactional
//	public UserDetails loadUserByUsername(String userName) {
//		User user = null;
//
//		if (userName.contains("@"))
//			user = userRepository.findByEmail(userName);
//		else
//			user = userRepository.findByUsername(userName);
//		if (user == null)
//			throw new BadCredentialsException("Bad credentials");
//
//		new AccountStatusUserDetailsChecker().check(user);
//		return user;
//	}


	@Override
	public AllUserPermissionsDto getUserPermissions(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		return userMapper.convertToAllUserPermissionsDto(user);
	}

	@Override
	public AllUserPermissionsDto getUserPermissions(UUID userUUID) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUUID(userUUID);
		AllUserPermissionsDto  allUserPermissionsDto = userMapper.convertToAllUserPermissionsDto(user);
		return allUserPermissionsDto;
	}
	
	@Override
	public UserDto getUser(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		UserDto  userDto = userMapper.convertToUserDto(user);
		return userDto;
	}

	@Override
	public UserInfoDto getUserInfo(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		UserInfoDto  userInfoDto = userMapper.convertToUserInfoDto(user);
		return userInfoDto;
	}


	@Override
	public DetailedUserInfoDto getDetailedUserInfo(String username) throws UserNotFoundException {
		User user = userRepository.findByUsername(username);
		DetailedUserInfoDto  detailedUserInfoDto = userMapper.convertToDetailedUserInfoDto(user);
		return detailedUserInfoDto;
	}


	@Override
	public UserStatusDto getUserStatus(String username) throws UserNotFoundException {
		User user = userRepository.findByUsername(username);
		UserStatusDto userStatusDto = userMapper.convertToUserStatusDto(user);
		return userStatusDto;
		
	}


	@Transactional
	@Override
	public UserDto addUser(UserDto userDto) throws UserAlreadyExistsException{
		// TODO Auto-generated method stub
 		User user = userMapper.convertToUser(userDto);
		user.addMetaData(user.getUsername());
		user.setAccount(null);
		user.setUserPermissions(null);
		user.setAccountSubscription(null);
		user=userRepository.save(user);
		String username = user.getUsername();
		String subscriptionCode = userDto.getAccountSubscription().getSubscription().getSubscriptionCode();
		System.out.println("subscriptionCode " + subscriptionCode);
		UUID accountId = userDto.getAccount().getId(); 
		saveAccountAndSubscriptionData(username,subscriptionCode,accountId);
		user = userRepository.getById(user.getId());
		return userMapper.convertToUserDto(user);
	}
	@Transactional
	@Override
	public UserDto addUser(UserAddDto userAddDto, String subscriptionCode, UUID accountId) throws UserAlreadyExistsException{
		// TODO Auto-generated method stub
		User user = userMapper.convertUserAddDtoToUser(userAddDto);
		user.addMetaData(user.getUsername());
		user.setUserPermissions(null);
		user=userRepository.save(user);
		
		saveAccountAndSubscriptionData(user.getUsername(), subscriptionCode ,accountId);
		user = userRepository.getById(user.getId());
		return userMapper.convertToUserDto(user);
	}
	
	@Override
	public User saveAccountAndSubscriptionData(String username, String subscriptionCode, UUID accountId) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		System.out.println(user.getUsername());
		AccountSubscription accSubscription = new AccountSubscription();
		System.out.println("checking ");
		Optional<Subscription> subscription = subscriptionRepository.findBySubscriptionCode(subscriptionCode);
		
		Optional<Account> optAccount = null;
		if(accountId!=null) {
			optAccount= accountRepository.findById(accountId);
		}
		Account account=  new Account();
		if(optAccount!=null && optAccount.isPresent()) {
			System.out.println("account found ");
			account = optAccount.get();
			user.setAccount(account);
		}else {
			System.out.println("account not found ");
			account.setAccountEmail(username); 
			account.setAccountStatus(EStatus.ACTIVE);
			account.setUsers(null);
			account.setDefaultUser(null);
			if(subscription.isPresent()) {
				List<AccountSubscription> accountSubscriptions = new ArrayList<>();
				accSubscription = new AccountSubscription(UUID.randomUUID(),subscription.get() ,username, new Date(),new Date(),EStatus.ACTIVE);
				accountSubscriptions.add(accSubscription);
				account.setAccountSubscriptions(accountSubscriptions);
				account = accountRepository.save(account);
		}
		}
		
		userRepository.updateUserAccount(account.getId(),username);
		accSubscription = accountSubscriptionRepository.findAccountSubscription(account.getId(), subscription.get().getId());
		if(accSubscription!=null) {
			userRepository.updateUserAccountSubscription(accSubscription.getId(), username);
		}
		
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDto editUser(UserDto userDto)  throws UserNotFoundException{
		// TODO Auto-generated method stub
		User user = userRepository.findByUUID(userDto.getId());
		user.editMetaData(user.getUsername());
		userMapper.updateUser(userDto,user);
		return userMapper.convertToUserDto( userRepository.save(user));
	}
	

	@Override
	public String deleteUser(String username) throws UserNotFoundException {
		User user = userRepository.findByUsername(username);
		userRepository.deleteUserRestaurantForUser(user.getId());
		userRepository.deleteByUserName(username);
		
		return "User Deleted Successfully!";
	}


	
	@Override
	public UserDto findByUUID(UUID userUUID) {
		// TODO Auto-generated method stub
		User user =userRepository.findByUUID(userUUID);
		return userMapper.convertToUserDto(user);
	}

	@Override
	public UserRestaurantDto getRestaurantsForUser(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		UserRestaurantDto userRestaurantDto = userMapper.convertToUserRestaurantDto(user);
		return userRestaurantDto;
	}

	@Override
    public List<UserDto> getAllUserInfo() throws UserNotFoundException {
        // TODO Auto-generated method stub
        List<User> user = userRepository.findAll();
        return userMapper.convertToUserListDto(user);
    }

	@Override
    public List<UserDto> getUserInfoByRestaurantId(UUID restaurantId) throws UserNotFoundException {
        // TODO Auto-generated method stub
         Restaurant restaurant = restaurantRepository.findByUUID(restaurantId);
        
        Set<User> user = restaurant.getUsers();
        List<UserDto>  userInfoDto = userMapper.convertToUserListDto(new ArrayList(user));       
        return userInfoDto;
    }

	@Override
	public List<UserDto> getAllUserInfo(int limit, int pageNo) throws UserNotFoundException {
		 List<User> users = userRepository.findPagewiseUsers(limit,pageNo*limit);
	        return userMapper.convertToUserListDto(users);
	}
	
	@Override
	public void addRestaurantToSuperAdmins(UUID restaurantId) throws UserNotFoundException{
		// TODO Auto-generated method stub
		List<User> superAdmins = userRepository.findSuperAdmins();
		superAdmins.forEach(superAdmin -> restaurantRepository.addRestaurantToUser(superAdmin.getId(), restaurantId));
			}

	@Override
	public UserDto addROSTeam(UserAddDto userDto) throws UserAlreadyExistsException {
		User user = userMapper.convertUserAddDtoToUser(userDto);
		user.addMetaData(user.getUsername());	
		Optional<Account>masterAccount = accountRepository.getROSTeamAccount();
		Account account = masterAccount.isPresent() ? masterAccount.get() : createROSTeamAccount();
		AccountSubscription masterAccountSubscription = accountSubscriptionRepository.getMasterAccountSubscription(account.getId());
		user.setAccount(account);
		user.setAccountSubscription(masterAccountSubscription);
		user = userRepository.save(user);
		try {
			userPermissionsService.setAllUserPermissions(userDto.getUsername(),masterAccountSubscription.getSubscription().getSubscriptionCode());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user.getRole().getName().equals(ERole.ROLE_SUPERADMIN)) {
			addRestaurantsForSuperAdmin(user.getId());
		}
		return userMapper.convertToUserDto(user);
	}
	
	private void addRestaurantsForSuperAdmin(UUID userId) {
		// TODO Auto-generated method stub
		List<Restaurant> restaurants= restaurantRepository.findAll();
		
		List<UUID> restaurantIds= restaurants.stream()
								.map(restaurant -> restaurant.getId())
								.collect(Collectors.toList());
		String s="";
			try {
				s =restaurantService.addRestaurantsToUser(userId, restaurantIds);
			} catch (RestaurantNotFoundException | UserRestaurantAlreadyExistsException e) {
				System.out.println(e.getMessage());
			}
	}
	@Transactional
	private Account createROSTeamAccount() {
		Account account = new Account();
		account.setId(UUID.randomUUID());
		account.setAccountEmail("SuperAdmin@Ros.com");
		account.setAccountStatus(EStatus.ACTIVE);
		account.setClientName("Super Admin");
		account.addMetaData("SuperAdmin@Ros.com");
		List<String> subscriptionCodes = new ArrayList<String>();
		subscriptionCodes.add("ERP_PRM");
		account.setAccountSubscriptions(clientService.createAccountSubscriptionsByCode(subscriptionCodes,"SuperAdmin@Ros.com"));
		account = accountRepository.save(account);
		return account;
	}

	@Override
	@Transactional
	public User saveClientAsUser(ClientAddDto clientAddDto) throws PrimaryContactNotFoundException {
		User user = new User();
//		user.setEmail(client.getContacgetContactEmail());
		user.setRole(userMapper.ToRole(ERole.ROLE_CLIENTADMIN));
		user.setUsername(clientAddDto.getPrimaryContact().getPrimaryContactEmail());
		user.setEmail(clientAddDto.getPrimaryContact().getPrimaryContactEmail());
		user.setUserStatus(EStatus.ACTIVE);
		user.setAzureUPN(clientAddDto.getAzureUPN());
//		System.out.println("Client AzureUPN is: " +  clientAddDto.getAzureUPN());
		user.setUserProfile(setUserProfileForClient(clientAddDto));
		user.addMetaData(clientAddDto.getPrimaryContact().getPrimaryContactEmail());
		
		// client user pin verification
		
		List<User> tabletUsers = userRepository.findUsersForTablet();
		
		String clientPin = clientAddDto.getClientPin();
		
		if(clientPin != null) {
		
			boolean duplicatePinCheck = false;
		
			for(User tabletUser: tabletUsers) {
				
				
				if(tabletUser.getUserPinHash() != null && ! duplicatePinCheck) {
					
					if (BCrypt.checkpw(clientPin, tabletUser.getUserPinHash()))
						duplicatePinCheck = true;
					
				}
				
			}
			if(duplicatePinCheck)
				throw new PrimaryContactNotFoundException("Please select a unique Pin for the Client!");
			
			user.setUserPinHash( BCrypt.hashpw(clientPin, BCrypt.gensalt()));
			
		}
		
		else
			user.setUserPinHash(null);
		
		return userRepository.save(user);
	}
	
	@Override
	public UserProfile setUserProfileForClient(ClientAddDto client) {
		// TODO Auto-generated method stub
		UserProfile userProfile = new UserProfile();
//		userProfile.setDob(clientEntity.getPrimaryContact().g);
//		userProfile.setGender(client.getClientProfile().getGender());
//		userProfile.setEmail(client.getClientProfile().getOwnerEmail());
		userProfile.setFirstName(client.getPrimaryContact().getFirstName());
		userProfile.setLastName(client.getPrimaryContact().getLastName());
		userProfile.setPersonalEmail(client.getPrimaryContact().getPrimaryContactEmail());
		userProfile.setEmail(client.getPrimaryContact().getPrimaryContactEmail());
		userProfile.setPhoneNo(client.getPrimaryContact().getPrimaryContactPhoneNo());
		userProfile.setImageURL("https://icon-library.com/images/clients-icon-png/clients-icon-png-9.jpg");
		return userProfile;
	}

	@Override
	public UserDto editUserStatus(UUID userId, EStatus status) throws UserNotFoundException{
		User user =userRepository.findByUUID(userId);
		user.setUserStatus(status);
		user= userRepository.save(user);
		return userMapper.convertToUserDto(user);
	}

	@Override
	public UserDetailsDto getUserDetails(String username) throws UserNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if(user == null)
			throw new UserNotFoundException("User with username: "+ username + " doesnot exists!");
		
		UserDetailsDto userDetailsDto = userMapper.convertToUserDetailsDto(user);
		
		Client client = user.getRestaurants().iterator().next().getClient();
		
		ClientDto clientDto = clientMapper.convertToClientDto(client);
		
		userDetailsDto.setClient(clientDto);
		
		
		return userDetailsDto;
	}

	@Override
	public String addROSTeamPin(String username, String pin) throws Exception {
		User user = userRepository.findByUsername(username);
		
		if(user == null)
			throw new Exception("User with username: "+ username + " doesnot exists!");
		
		if(user.getRole().getName().equals(com.ros.administration.model.enums.ERole.ROLE_SUPERADMIN) || user.getRole().getName().equals(com.ros.administration.model.enums.ERole.ROLE_ACCOUNTOFFICER) || user.getRole().getName().equals(com.ros.administration.model.enums.ERole.ROLE_CLIENTADMIN))  {
			
			List<User> tabletUsers = userRepository.findUsersForTablet();
			
			
			
				boolean duplicatePinCheck = false;
			
				for(User tabletUser: tabletUsers) {
					
					
					if(tabletUser.getUserPinHash() != null && !duplicatePinCheck) {
						
						if (BCrypt.checkpw(pin, tabletUser.getUserPinHash()))
							duplicatePinCheck = true;
						
					}
					
				}
				if(duplicatePinCheck)
					throw new Exception("Please select a unique Pin for the User!");
				
				user.setUserPinHash( BCrypt.hashpw(pin, BCrypt.gensalt()));
				
				userRepository.save(user);
				
				return "Pin set successfully for username: " + username;
			
		}
		
		return "User is not a SuperAdmin or an Account officer or a Client Admin";
	}

	@Override
	public String editTabletPin(String username,String oldPin, String newpin) throws Exception {

		User user = userRepository.findByUsername(username);
		
		if(user == null)
			throw new Exception("User with username: "+ username + " doesnot exists!");
		
		
		
		if(user.getRole().getName().equals(com.ros.administration.model.enums.ERole.ROLE_SUPERADMIN) || user.getRole().getName().equals(com.ros.administration.model.enums.ERole.ROLE_ACCOUNTOFFICER) || user.getRole().getName().equals(com.ros.administration.model.enums.ERole.ROLE_CLIENTADMIN))  {
			
			if(user.getUserPinHash() == null)
				throw new Exception(" Pin doesnot exists for this user!");
				
				if(! (BCrypt.checkpw(oldPin, user.getUserPinHash())))
					throw new Exception("Old Pin doesnot Match!");	
			
			
			List<User> tabletUsers = userRepository.findUsersForTablet();
			
			
			
				boolean duplicatePinCheck = false;
			
				for(User tabletUser: tabletUsers) {
					
					
					if(tabletUser.getUserPinHash() != null && !duplicatePinCheck) {
						
						if (BCrypt.checkpw(newpin, tabletUser.getUserPinHash()))
							duplicatePinCheck = true;
						
					}
					
				}
				if(duplicatePinCheck)
					throw new Exception("Please select a unique Pin for the User!");
				
				user.setUserPinHash( BCrypt.hashpw(newpin, BCrypt.gensalt()));
				
				userRepository.save(user);
				
				return "Pin set successfully for username: " + username;
			
		}
		
		return "User is not a SuperAdmin or an Account officer or a Client Admin";
	}

	@Override
	public Set<RestaurantDto> validateEntryPin(String pin) throws Exception {

		List<User> tabletUsers = userRepository.findUsersForTablet();
		
		Set<RestaurantDto> restaurantDtos = new HashSet<RestaurantDto>();
		
		boolean pinMatch = false;

		
		for(User tabletUser: tabletUsers) {
			
			if (tabletUser.getUserPinHash() != null && BCrypt.checkpw(pin, tabletUser.getUserPinHash())) {
				
				pinMatch = true;
				
				Set<Restaurant> restaurants = tabletUser.getRestaurants();
				
				for(Restaurant restaurant : restaurants)
					restaurantDtos.add(	restaurantMapper.convertToRestaurantDto(restaurant));
					
			}

		}

		if(!pinMatch)
			throw new Exception("Old Pin doesnot Match!");	
		
		return restaurantDtos;
	}
	
	

}
