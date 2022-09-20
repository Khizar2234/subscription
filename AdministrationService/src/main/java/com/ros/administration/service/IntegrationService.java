package com.ros.administration.service;

import java.util.List;
import java.util.UUID;

//import org.springframework.security.authentication.AccountStatusRestaurantDetailsChecker;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.restaurantdetails.RestaurantDetails;
//import org.springframework.security.core.restaurantdetails.RestaurantDetailsService;
//import org.springframework.security.core.restaurantdetails.RestaurantnameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ros.administration.controller.dto.IntegrationDto;
import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.exceptions.IntegrationNotFoundException;
import com.ros.administration.exceptions.RestaurantAlreadyExistsException;
import com.ros.administration.exceptions.RestaurantIntegrationAlreadyExistsException;
import com.ros.administration.exceptions.RestaurantNotFoundException;


@Service
public interface IntegrationService {


	List<IntegrationDto> getAllIntegrations()throws IntegrationNotFoundException;
	
	IntegrationDto saveIntegration(IntegrationDto integration) throws IntegrationNotFoundException;

	IntegrationDto editIntegrationStatus(UUID id, boolean integrationStatus) throws IntegrationNotFoundException;

	boolean deleteIntegration(UUID id)throws IntegrationNotFoundException;
	
	
}
