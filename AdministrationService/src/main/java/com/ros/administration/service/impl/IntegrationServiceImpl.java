package com.ros.administration.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AccountStatusRestaurantDetailsChecker;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.restaurantdetails.RestaurantDetails;
//import org.springframework.security.core.restaurantdetails.RestaurantDetailsService;
//import org.springframework.security.core.restaurantdetails.RestaurantnameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ros.administration.controller.dto.IntegrationDto;
import com.ros.administration.exceptions.IntegrationNotFoundException;
import com.ros.administration.mapper.IntegrationMapper;
import com.ros.administration.model.Integration;
import com.ros.administration.repository.IntegrationRepository;
import com.ros.administration.repository.RestaurantRepository;
import com.ros.administration.service.IntegrationService;

@Service
public class IntegrationServiceImpl implements IntegrationService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private IntegrationRepository integrationRepository;

	@Autowired
	private IntegrationMapper integrationMapper;

	@Override
	public List<IntegrationDto> getAllIntegrations() {
		return integrationMapper.convertToIntegrationDtoList(integrationRepository.findAllIntegrations());
	}

	@Override
	public IntegrationDto editIntegrationStatus(UUID id, boolean integrationStatus) {
		Integration integration = integrationRepository.getOne(id);
		integration = integrationRepository.save(integration);
		return integrationMapper.convertToIntegrationDto(integration);

	}

	@Override
	public boolean deleteIntegration(UUID id) throws IntegrationNotFoundException {
		Optional<Integration> integration = integrationRepository.findById(id);
		if (integration.isPresent()) {
			integrationRepository.deleteById(id);
			return true;
		}
		return false;

	}

	@Override
	public IntegrationDto saveIntegration(IntegrationDto integrationDto) throws IntegrationNotFoundException {

		Integration integration = integrationMapper.convertToIntegration(integrationDto);
		integration.addMetaData();

		return integrationMapper.convertToIntegrationDto(integrationRepository.save(integration));
	}

}
