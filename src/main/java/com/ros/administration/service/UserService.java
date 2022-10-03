package com.ros.administration.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

//import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.controller.dto.account.ClientAddDto;
import com.ros.administration.controller.dto.account.user.AllUserPermissionsDto;
import com.ros.administration.controller.dto.account.user.DetailedUserInfoDto;
import com.ros.administration.controller.dto.account.user.UserAddDto;
import com.ros.administration.controller.dto.account.user.UserDetailsDto;
import com.ros.administration.controller.dto.account.user.UserDto;
import com.ros.administration.controller.dto.account.user.UserInfoDto;
import com.ros.administration.controller.dto.account.user.UserRestaurantDto;
import com.ros.administration.controller.dto.account.user.UserStatusDto;
import com.ros.administration.exceptions.PrimaryContactNotFoundException;
import com.ros.administration.exceptions.UserAlreadyExistsException;
import com.ros.administration.exceptions.UserNotFoundException;
import com.ros.administration.model.Restaurant;
import com.ros.administration.model.account.User;
import com.ros.administration.model.account.UserProfile;
import com.ros.administration.model.enums.EStatus;

@Service
public interface UserService {


//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	AllUserPermissionsDto getUserPermissions(String username)throws UserNotFoundException;
	
	AllUserPermissionsDto getUserPermissions(UUID userUUID)throws UserNotFoundException;
	
	UserInfoDto getUserInfo (String username) throws UserNotFoundException;
	
	public UserStatusDto getUserStatus (String username) throws UserNotFoundException;
	
	public UserRestaurantDto getRestaurantsForUser(String username) throws UserNotFoundException;
	
	UserDto addUser (UserDto user) throws UserAlreadyExistsException;

	UserDto findByUUID(UUID userUUID);

	String deleteUser(String username) throws UserNotFoundException;

	DetailedUserInfoDto getDetailedUserInfo(String username) throws UserNotFoundException;

	UserDto editUser(UserDto userDto) throws UserNotFoundException;

	UserDto getUser(String username) throws UserNotFoundException;

	List<UserDto> getAllUserInfo()  throws UserNotFoundException;

	List<UserDto> getUserInfoByRestaurantId(UUID restaurantId)throws UserNotFoundException;

	UserDto addUser(UserAddDto userAddDto, String subscriptionCode, UUID accountId) throws UserAlreadyExistsException;

	List<UserDto> getAllUserInfo(int limit, int pageNo)throws UserNotFoundException;

	void addRestaurantToSuperAdmins(UUID id) throws UserNotFoundException;

	UserDto addROSTeam(UserAddDto userDto) throws UserAlreadyExistsException;

	User saveClientAsUser(ClientAddDto clientAddDto)throws PrimaryContactNotFoundException;

	UserProfile setUserProfileForClient(ClientAddDto client);

	User saveAccountAndSubscriptionData(String username, String subscriptionCode, UUID accountId);

	UserDto editUserStatus(UUID userId, EStatus status)throws UserNotFoundException;
	
	UserDetailsDto getUserDetails(String username) throws UserNotFoundException;
	
	String addROSTeamPin(String username, String pin) throws Exception;
	
	Set<RestaurantDto> validateEntryPin(String pin) throws Exception;

	String editTabletPin(String username, String oldPin, String newpin) throws Exception;
	
	List<UUID> getUserIdsFromRestaurantId(UUID restaurantId);
	
    int totalCountOfUsersFromrestaurants(List<Restaurant> restaurants);

    int totalEmployeesFromRestaurants(List<Restaurant> restaurants);
}
