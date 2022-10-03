package com.ros.administration.service;

import java.util.List;
import java.util.UUID;

import com.ros.administration.controller.dto.CurrencyDto;
import com.ros.administration.controller.dto.configuration.DepartmentConfigurationDto;
import com.ros.administration.controller.dto.configuration.RestaurantConfigurationDto;
import com.ros.administration.controller.dto.configuration.RestaurantShiftsDto;
import com.ros.administration.controller.dto.configuration.ShiftConfigurationDto;
import com.ros.administration.exceptions.*;
import com.ros.administration.model.configuration.PDQCard;
import com.ros.administration.model.configuration.PDQMachine;
import com.ros.administration.model.configuration.ShiftConfiguration;
import com.ros.administration.model.configuration.ThirdPartySource;

public interface RestaurantConfigurationService {
//
	RestaurantConfigurationDto addConfiguration(RestaurantConfigurationDto restaurantConfig)
			throws ConfigurationNotFoundException;


	ShiftConfigurationDto addShiftConfiguration(ShiftConfigurationDto shiftConfigurationDto) throws ConfigurationNotFoundException;

	ShiftConfigurationDto editShiftConfiguration(ShiftConfigurationDto shiftConfigurationDto) throws ConfigurationNotFoundException;

	String deleteShiftConfiguration(UUID shiftId, UUID id) throws ConfigurationNotFoundException;

	ShiftConfigurationDto getShiftConfigurationById(UUID id) throws ConfigurationNotFoundException;

	DepartmentConfigurationDto addDepartmentConfiguration(DepartmentConfigurationDto departmentConfigurationDto) throws ConfigurationNotFoundException;

	DepartmentConfigurationDto editDepartmentConfiguration(DepartmentConfigurationDto departmentConfigurationDto) throws ConfigurationNotFoundException;

	DepartmentConfigurationDto getDepartmentConfigurationById(UUID id) throws ConfigurationNotFoundException;

	String deleteDepartmentConfiguration(UUID id, UUID customId) throws ConfigurationNotFoundException;

	RestaurantConfigurationDto updateConfiguration(RestaurantConfigurationDto restaurantConfig, UUID restaurantId)
			throws ConfigurationNotFoundException;

	RestaurantConfigurationDto getConfigurationById(UUID id) throws ConfigurationNotFoundException;

	List<String> getAllThirdPartyList() throws ConfigurationNotFoundException;

	List<String> getAllPettyCashTypeList() throws ConfigurationNotFoundException;
	
	List<String> getAllPDQMachines() throws ConfigurationNotFoundException;
	
	RestaurantConfigurationDto findByResaurantId(UUID id) throws ConfigurationNotFoundException;

	List<CurrencyDto> getAllCurrencyList() throws ConfigurationNotFoundException;

	List<ThirdPartySource> addThirdPartySources(List<ThirdPartySource> thirdPartySources) throws ThirdPartySourceNotFoundException;

	List<PDQCard> addPdqCards(List<PDQCard> pdqCards) throws PDQCardNotFoundException;

	List<PDQMachine> addPdqMachines(List<PDQMachine> pdqMachines) throws PDQMachineNotFoundException;

	List<String> getAllPDQCards() throws PDQCardNotFoundException;

    List<CurrencyDto> saveCurrencyList(List<CurrencyDto> currencyDtos) throws CurrencyAlreadyExistsException;
}
