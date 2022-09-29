package com.ros.administration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ros.administration.controller.dto.DepartmentDto;
import com.ros.administration.controller.dto.configuration.*;
import com.ros.administration.exceptions.*;
import com.ros.administration.mapper.DepartmentConfigurationMapper;
import com.ros.administration.mapper.ShiftConfigurationMapper;
import com.ros.administration.model.configuration.*;
import com.ros.administration.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.administration.controller.dto.CurrencyDto;
import com.ros.administration.log.RosLogDebug;
import com.ros.administration.mapper.RestaurantConfigurationMapper;
import com.ros.administration.model.Restaurant;
import com.ros.administration.service.RestaurantConfigurationService;

@Service
public class RestaurantConfigurationServiceImpl implements RestaurantConfigurationService {
//

	@Autowired
	private DepartmentConfigurationMapper departmentConfigurationMapper;

	@Autowired
	private DepartmentConfigurationRepository departmentConfigurationRepository;

	@Autowired
	private RestaurantConfigurationRepository restaurantConfigurationRepository;

	@Autowired
	private ThirdPartySourceRepository thirdPartySourceRepository;
	
	@Autowired
	private PDQMachineRepository pdqMachineRepository;
	
	@Autowired
	private PDQCardRepository pdqCardRepository;


	@Autowired
	private ShiftConfigurationMapper shiftConfigurationMapper;

	@Autowired
	private ShiftConfigurationRepository shiftConfigurationRepository;

	@Autowired
	private RestaurantConfigurationMapper restaurantConfigurationMapper;

	@Autowired
	private CashnPdqRepository cashnPdqRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private CurrencyRepository currencyRepository;

	@Override
	@RosLogDebug
	public RestaurantConfigurationDto addConfiguration(RestaurantConfigurationDto restaurantConfig) {

		RestaurantConfiguration entity = restaurantConfigurationMapper
				.convertToRestaurantConfigEntity(restaurantConfig);
		return restaurantConfigurationMapper
				.convertToRestaurantConfigDto(restaurantConfigurationRepository.save(entity));
	}

	@Override
	public ShiftConfigurationDto addShiftConfiguration(ShiftConfigurationDto shiftConfigurationDto) throws ConfigurationNotFoundException {

//		ShiftConfiguration shiftConfiguration = shiftConfigurationMapper.convertToShiftConfigurationEntity(shiftConfigurationDto);
//		return shiftConfigurationMapper.convertToShiftConfigurationDto(shiftConfigurationRepository.save(shiftConfiguration));

		if (shiftConfigurationRepository.existsById(shiftConfigurationDto.getId())) {

			ShiftConfigurationDto restaurantShifts = shiftConfigurationMapper.convertToShiftConfigurationDto(shiftConfigurationRepository.getOne(shiftConfigurationDto.getId()));
			RestaurantShiftsDto restaurantShiftsDtoNew = shiftConfigurationDto.getRestaurantShifts().get(0);

			restaurantShifts.getRestaurantShifts().add(restaurantShiftsDtoNew);
			ShiftConfiguration shiftConfiguration = shiftConfigurationMapper.convertToShiftConfigurationEntity(restaurantShifts);
			return shiftConfigurationMapper.convertToShiftConfigurationDto(shiftConfigurationRepository.save(shiftConfiguration));

		} else {
			ShiftConfiguration shiftConfiguration = shiftConfigurationMapper.convertToShiftConfigurationEntity(shiftConfigurationDto);
			return shiftConfigurationMapper.convertToShiftConfigurationDto(shiftConfigurationRepository.save(shiftConfiguration));

		}

	}

	@Override
	public ShiftConfigurationDto editShiftConfiguration(ShiftConfigurationDto shiftConfigurationDto) throws ConfigurationNotFoundException {
		ShiftConfigurationDto restaurantShifts = shiftConfigurationMapper.convertToShiftConfigurationDto(shiftConfigurationRepository.getOne(shiftConfigurationDto.getId()));
		UUID shiftId = shiftConfigurationDto.getRestaurantShifts().get(0).getShiftId();
		RestaurantShiftsDto restaurantShiftsDtoNew = shiftConfigurationDto.getRestaurantShifts().get(0);
		int i = 0;
		for (RestaurantShiftsDto restaurantShiftsDto : restaurantShifts.getRestaurantShifts()) {
			if (restaurantShiftsDto.getShiftId().equals(shiftId)) {
				break;

			}
			i++;
		}

		if (i < restaurantShifts.getRestaurantShifts().size()) {
			restaurantShifts.getRestaurantShifts().remove(i);
		}

		restaurantShifts.getRestaurantShifts().add(restaurantShiftsDtoNew);
		ShiftConfiguration shiftConfiguration = shiftConfigurationMapper.convertToShiftConfigurationEntity(restaurantShifts);
		return shiftConfigurationMapper.convertToShiftConfigurationDto(shiftConfigurationRepository.save(shiftConfiguration));
	}

	@Override

	public String deleteShiftConfiguration(UUID shiftId, UUID id) throws ConfigurationNotFoundException{

		ShiftConfigurationDto restaurantShifts = shiftConfigurationMapper.convertToShiftConfigurationDto(shiftConfigurationRepository.getOne(id));
		int i = 0;
		for (RestaurantShiftsDto restaurantShiftsDto : restaurantShifts.getRestaurantShifts()) {
			if (restaurantShiftsDto.getShiftId().equals(shiftId)) {
				break;

			}
			i++;
		}

		if (i < restaurantShifts.getRestaurantShifts().size()) {
			restaurantShifts.getRestaurantShifts().remove(i);
		}

		ShiftConfiguration shiftConfiguration = shiftConfigurationMapper.convertToShiftConfigurationEntity(restaurantShifts);
		shiftConfigurationMapper.convertToShiftConfigurationDto(shiftConfigurationRepository.save(shiftConfiguration));
		return "Shift Configuration Deleted Successfully";

	}




	@Override
	public ShiftConfigurationDto getShiftConfigurationById(UUID id) throws ConfigurationNotFoundException {
		if(shiftConfigurationRepository.existsById(id)){
			ShiftConfiguration shiftConfiguration = shiftConfigurationRepository.getOne(id);
			return shiftConfigurationMapper.convertToShiftConfigurationDto(shiftConfiguration);
		}
		else{
			throw new ConfigurationNotFoundException("Configuration does not exist");
		}
	}

	@Override
	public DepartmentConfigurationDto addDepartmentConfiguration(DepartmentConfigurationDto departmentConfigurationDto) throws ConfigurationNotFoundException {
		if(departmentConfigurationRepository.existsById(departmentConfigurationDto.getId())){
			DepartmentConfigurationDto departmentConfigurationDto1 = departmentConfigurationMapper.convertToDepartmentConfigurationDto(departmentConfigurationRepository.getOne(departmentConfigurationDto.getId()));

			CustomDepartmentDto departmentDto = departmentConfigurationDto.getCustomDepartment().get(0);

			departmentConfigurationDto1.getCustomDepartment().add(departmentDto);
			DepartmentConfiguration departmentConfiguration = departmentConfigurationMapper.convertToDepartmentConfigurationEntity(departmentConfigurationDto1);
			return departmentConfigurationMapper.convertToDepartmentConfigurationDto(departmentConfigurationRepository.save(departmentConfiguration));
		}else{
			DepartmentConfiguration departmentConfiguration = departmentConfigurationMapper.convertToDepartmentConfigurationEntity(departmentConfigurationDto);
			return  departmentConfigurationMapper.convertToDepartmentConfigurationDto(departmentConfigurationRepository.save(departmentConfiguration));
		}
	}

	@Override
	public DepartmentConfigurationDto editDepartmentConfiguration(DepartmentConfigurationDto departmentConfigurationDto) throws ConfigurationNotFoundException {
		DepartmentConfigurationDto departmentConfigurationDto1 = departmentConfigurationMapper.convertToDepartmentConfigurationDto(departmentConfigurationRepository.getOne(departmentConfigurationDto.getId()));
		UUID id = departmentConfigurationDto.getCustomDepartment().get(0).getCustomId();
		CustomDepartmentDto departmentDto = departmentConfigurationDto.getCustomDepartment().get(0);
		int i = 0;
		for (CustomDepartmentDto departmentDto1 : departmentConfigurationDto1.getCustomDepartment()) {
			if (departmentDto1.getCustomId().equals(id)) {
				break;

			}
			i++;
		}

		if (i < departmentConfigurationDto1.getCustomDepartment().size()) {
			departmentConfigurationDto1.getCustomDepartment().remove(i);
		}

		departmentConfigurationDto1.getCustomDepartment().add(departmentDto);
		DepartmentConfiguration departmentConfiguration = departmentConfigurationMapper.convertToDepartmentConfigurationEntity(departmentConfigurationDto1);
		return departmentConfigurationMapper.convertToDepartmentConfigurationDto(departmentConfigurationRepository.save(departmentConfiguration));
	}

	@Override
	public DepartmentConfigurationDto getDepartmentConfigurationById(UUID id) throws ConfigurationNotFoundException {
		if(departmentConfigurationRepository.existsById(id)){
		DepartmentConfiguration departmentConfiguration = departmentConfigurationRepository.getOne(id);
		return departmentConfigurationMapper.convertToDepartmentConfigurationDto(departmentConfiguration);
	}else
		throw new ConfigurationNotFoundException("Department Configuration Not Found");
}

	@Override
	public String deleteDepartmentConfiguration(UUID id, UUID customId) throws ConfigurationNotFoundException {
		DepartmentConfigurationDto departmentConfigurationDto1 = departmentConfigurationMapper.convertToDepartmentConfigurationDto(departmentConfigurationRepository.getOne(id));
		int i = 0;
		for (CustomDepartmentDto departmentDto1 : departmentConfigurationDto1.getCustomDepartment()) {
			if (departmentDto1.getCustomId().equals(customId)) {
				break;

			}
			i++;
		}

		if (i < departmentConfigurationDto1.getCustomDepartment().size()) {
			departmentConfigurationDto1.getCustomDepartment().remove(i);
		}

		DepartmentConfiguration departmentConfiguration = departmentConfigurationMapper.convertToDepartmentConfigurationEntity(departmentConfigurationDto1);
		departmentConfigurationMapper.convertToDepartmentConfigurationDto(departmentConfigurationRepository.save(departmentConfiguration));
		return "Department Configuration Deleted Successfully";
	}


	@Override
	@RosLogDebug
	public RestaurantConfigurationDto updateConfiguration(RestaurantConfigurationDto restaurantConfigDto,
			UUID restaurantId) throws ConfigurationNotFoundException {

		RestaurantConfiguration configuration = restaurantConfigurationMapper
				.convertToRestaurantConfigEntity(restaurantConfigDto);
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);

		Restaurant restaurant = null;

		if (optionalRestaurant.isPresent()) {

			restaurant = optionalRestaurant.get();

			if (restaurant.getRestaurantConfiguration() != null) {

				configuration = restaurantConfigurationRepository
						.getOne(restaurant.getRestaurantConfiguration().getId());
				restaurantConfigurationMapper.updateRestaurantConfig(restaurantConfigDto, configuration);
				// restaurant.get().setRestaurantConfiguration(configuration);
				restaurant = restaurantRepository.save(restaurant);
			} else {
				restaurant.setRestaurantConfiguration(configuration);
				restaurant = restaurantRepository.save(restaurant);
			}
		}

		return restaurantConfigurationMapper.convertToRestaurantConfigDto(restaurant.getRestaurantConfiguration());
	}

	@Override
	public RestaurantConfigurationDto getConfigurationById(UUID id) {
		RestaurantConfiguration entity = restaurantConfigurationRepository.getOne(id);
		RestaurantConfigurationDto restaurantConfigurationDto;
		restaurantConfigurationDto = restaurantConfigurationMapper.convertToRestaurantConfigDto(entity);

		return restaurantConfigurationDto;
	}

	@Override
	public List<String> getAllThirdPartyList() {

		List<String> thirdPartyCategories = thirdPartySourceRepository.findUniqueThirdParties();
//		List<ThirdPartyCategoryDto> thirdPartyCategoryDtos = new ArrayList<>();
//		for (ThirdPartyCategory thirdPartyCategory : thirdPartyCategories) {
//			thirdPartyCategoryDtos
//					.add(restaurantConfigurationMapper.convertToThirdPartyCategoryDto(thirdPartyCategory));
//
//		}
		return thirdPartyCategories;
	}

	@Override
	public List<String> getAllPettyCashTypeList() throws ConfigurationNotFoundException {

		List<String> pettyCashTypes = cashnPdqRepository.findUniquePettyCashs();
//		List<PettyCashTypeDto> pettyCashTypeDtos = new ArrayList<>();
//		for (PettyCashType pettyCashType : pettyCashTypes) {
//			pettyCashTypeDtos.add(restaurantConfigurationMapper.convertToPettyCashTypeDto(pettyCashType));
//		}
		return pettyCashTypes;
	}

	@Override
	public List<String> getAllPDQMachines() throws ConfigurationNotFoundException {

		List<String> pdqMachines = cashnPdqRepository.findUniqueMachines();
//		List<PDQTypeDto> pdqTypeDtos = new ArrayList<>();
//		for (String machine: pdqMachines) {
//			pdqTypeDtos.add(new PDQTypeDto(machine, null, new PDQMachineDto(machine)));
//		}
		return pdqMachines;
	}

	@Override
	public RestaurantConfigurationDto findByResaurantId(UUID id) throws ConfigurationNotFoundException {

		Restaurant restaurant = null;
		RestaurantConfiguration entity = null;
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
		if (optionalRestaurant.isPresent()) {
			restaurant = optionalRestaurant.get();

			if (restaurant.getRestaurantConfiguration() != null) {
				entity = restaurant.getRestaurantConfiguration();
				RestaurantConfigurationDto restaurantConfigurationDto;
				return restaurantConfigurationDto = restaurantConfigurationMapper.convertToRestaurantConfigDto(entity);
			} else {
				return null;
			}
		}
		return null;

	}
	
	@Override

	public List<CurrencyDto> getAllCurrencyList() throws ConfigurationNotFoundException {

		List<Currency> currencyTypes = currencyRepository.findUniqueCurrencyType();

		List<CurrencyDto> currencyDtos = new ArrayList<>();

		for (Currency currencytype: currencyTypes) {

			currencyDtos.add(new CurrencyDto(currencytype.getId(), currencytype.getCurrencyType() ,currencytype.getSymbol()));

		}

		return currencyDtos;

	}

	 
	   


	    @Override
	    public List<PDQMachine> addPdqMachines(List<PDQMachine> pdqMachines) throws PDQMachineNotFoundException {
	    	pdqMachines.forEach(pdqMachine -> pdqMachine=pdqMachineRepository.save(pdqMachine));
	        return pdqMachines;
	    }

	 

	 

	 

	    @Override
	    public List<PDQCard> addPdqCards(List<PDQCard> pdqCards) throws PDQCardNotFoundException {
	        // TODO Auto-generated method stub
	    	pdqCards.forEach(pdqCard ->pdqCard= pdqCardRepository.save(pdqCard));
	    	
	        return pdqCards;
	    }

	 

	   

	 

	    @Override
	    public List<ThirdPartySource> addThirdPartySources(List<ThirdPartySource> thirdPartySources)
	            throws ThirdPartySourceNotFoundException {
	        // TODO Auto-generated method stub
	    	thirdPartySources.forEach(thirdPartySource -> thirdPartySource = thirdPartySourceRepository.save(thirdPartySource));
	        return thirdPartySources;
	    }

		@Override
		public List<String> getAllPDQCards() throws PDQCardNotFoundException {
			// TODO Auto-generated method stub
			List<String> pdqCards = cashnPdqRepository.findUniqueCards();
			return pdqCards;
		}

	@Override
	public List<CurrencyDto> saveCurrencyList(List<CurrencyDto> currencyDtos) throws CurrencyAlreadyExistsException {

		List<Currency> currencies =new ArrayList<>();
		currencyDtos.forEach(currencyDto -> currencies.add(new Currency(UUID.randomUUID(), currencyDto.getCurrencyType(), currencyDto.getSymbol())));
		currencies.forEach(currency -> {
			currency = currencyRepository.save(currency);
		});


		List<CurrencyDto> currencyDtoList = new ArrayList<>();

		for (Currency currency: currencies) {

			currencyDtoList.add(new CurrencyDto(currency.getId(), currency.getCurrencyType() ,currency.getSymbol()));

		}

		return currencyDtoList;

	}

	}

