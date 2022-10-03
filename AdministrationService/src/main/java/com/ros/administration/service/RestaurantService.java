package com.ros.administration.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.controller.dto.RestaurantIntegrationDto;
import com.ros.administration.exceptions.IntegrationNotFoundException;
//RestaurantOneSolution@dev.azure.com/RestaurantOneSolution/RestaurantOneSolution/_git/ROS_ADMINISTRATION-SERVICE
import com.ros.administration.exceptions.RestaurantAlreadyExistsException;
import com.ros.administration.exceptions.RestaurantIntegrationAlreadyExistsException;
import com.ros.administration.exceptions.RestaurantNotFoundException;
import com.ros.administration.exceptions.UserRestaurantAlreadyExistsException;
import com.ros.administration.model.Restaurant;

@Service
public interface RestaurantService {

//	public RestaurantDetails loadRestaurantByRestaurantname(String restaurantname) throws RestaurantnameNotFoundException;

	RestaurantDto addRestaurant(RestaurantDto restaurant) throws RestaurantAlreadyExistsException;

	RestaurantDto findByUUID(UUID restaurantUUID);

	String deleteRestaurant(UUID restaurantUUID) throws RestaurantNotFoundException;

	RestaurantDto editRestaurant(RestaurantDto restaurantDto) throws RestaurantNotFoundException;

	RestaurantDto addRestaurantIntegrations(RestaurantDto restaurantDto)
			throws RestaurantIntegrationAlreadyExistsException;

	RestaurantIntegrationDto editIntegrationStatus(UUID id, boolean integrationStatus, String integrationCredentials)
			throws IntegrationNotFoundException;

	List<RestaurantIntegrationDto> getRestaurantIntegrations(UUID restaurantId) throws RestaurantNotFoundException;

	List<RestaurantDto> getRestaurantDetails(UUID clientId) throws RestaurantNotFoundException;

	List<RestaurantDto> getRestaurantDetails() throws RestaurantNotFoundException;

	RestaurantDto getRestaurantById(UUID id) throws RestaurantNotFoundException;

	String addRestaurantToUser(UUID userId, UUID restaurantId)
			throws RestaurantNotFoundException, UserRestaurantAlreadyExistsException;

	String addRestaurantsToUser(UUID userId, List<UUID> restaurantIds)
			throws RestaurantNotFoundException, UserRestaurantAlreadyExistsException;

	String updateUserRestaurantConnections(UUID userId, List<UUID> restaurantIds);

	String updateUserRestaurant(UUID userId, UUID restaurantId)
			throws UserRestaurantAlreadyExistsException, RestaurantNotFoundException;

	List<RestaurantDto> getAllRestaurants(int limit, int pageNo) throws RestaurantNotFoundException;
	
	String setRestaurantPin(UUID clientId, UUID restaurantId, String pin) throws Exception;
	
	String editRestaurantPin(UUID clientId,UUID restaurantId, String oldPin, String newPin) throws Exception;

	String verifyRestaurantPin(UUID restaurantId, String pin) throws Exception;


    List<Restaurant> getListOfRestaurants(UUID clientId);


}
