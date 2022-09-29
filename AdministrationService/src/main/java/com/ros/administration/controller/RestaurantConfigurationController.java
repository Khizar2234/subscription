package com.ros.administration.controller;

import java.util.List;
import java.util.UUID;

import com.ros.administration.controller.dto.configuration.*;
import com.ros.administration.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ros.administration.controller.dto.CurrencyDto;
import com.ros.administration.log.RosLogDebug;
import com.ros.administration.model.configuration.PDQCard;
import com.ros.administration.model.configuration.PDQMachine;
import com.ros.administration.model.configuration.ThirdPartySource;
import com.ros.administration.service.RestaurantConfigurationService;
import com.ros.administration.util.ExceptionHandler;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/restconfiguration")
@CrossOrigin("*")
@Slf4j
public class RestaurantConfigurationController {

	@Autowired
	private RestaurantConfigurationService configurationService;


	@Operation(summary = "add restaurant shift configuration")
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> addShiftConfiguration(@RequestBody ShiftConfigurationDto shiftConfigurationDto){
		ResponseEntity<?> response;
		try{
			response = new ResponseEntity<ShiftConfigurationDto>(configurationService.addShiftConfiguration(shiftConfigurationDto),HttpStatus.OK);
		}catch(ConfigurationNotFoundException e){
			response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}

	@Operation(summary = "add restaurant dept configuration")
	@PostMapping("/deptConfig")
	@ResponseBody
	public ResponseEntity<?> addDepartmentConfiguration(@RequestBody DepartmentConfigurationDto departmentConfigurationDto){
		ResponseEntity<?> response;
		try{
			response = new ResponseEntity<DepartmentConfigurationDto>(configurationService.addDepartmentConfiguration(departmentConfigurationDto),HttpStatus.OK);
		}catch(ConfigurationNotFoundException e){
			response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}

	@Operation(summary = "Editing the Restaurant Shift Configuration")
	@PutMapping("/editDeptConfig")
	@ResponseBody
	public ResponseEntity<?> editDepartmentConfiguration(@RequestBody DepartmentConfigurationDto departmentConfigurationDto){
		ResponseEntity<?> response;
		try{
			response = new ResponseEntity<DepartmentConfigurationDto>(configurationService.editDepartmentConfiguration(departmentConfigurationDto),HttpStatus.OK);
		}catch(ConfigurationNotFoundException e){
			response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}

	@Operation(summary = "Editing the Restaurant Department Configuration")
	@PutMapping("/editShiftConfig")
	@ResponseBody
	public ResponseEntity<?> editShiftConfiguration(@RequestBody ShiftConfigurationDto shiftConfigurationDto){
		ResponseEntity<?> response;
		try{
			response = new ResponseEntity<ShiftConfigurationDto>(configurationService.editShiftConfiguration(shiftConfigurationDto),HttpStatus.OK);
		}catch(ConfigurationNotFoundException e){
			response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}




	@Operation(summary = "Get the Restaurant Shift Configuration By Id")
	@GetMapping("/getShiftConfigById/{id}")
	@ResponseBody
	public ResponseEntity<?> getShiftConfigurationById(@PathVariable(value = "id")UUID id){
		ResponseEntity<?> response;
		try{
			response = new ResponseEntity<ShiftConfigurationDto>(configurationService.getShiftConfigurationById(id),HttpStatus.OK);
		}catch(ConfigurationNotFoundException e){
			response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}

	@Operation(summary = "Get the Restaurant Department Configuration By Id")
	@GetMapping("/getDeptConfigById/{id}")
	@ResponseBody
	public ResponseEntity<?> getDepartmentConfigurationById(@PathVariable(value = "id")UUID id){
		ResponseEntity<?> response;
		try{
			response = new ResponseEntity<DepartmentConfigurationDto>(configurationService.getDepartmentConfigurationById(id),HttpStatus.OK);
		}catch(ConfigurationNotFoundException e){
			response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}





	@Operation(summary = "delete shift configuration by id")
	@DeleteMapping
	public ResponseEntity<?> deleteShiftConfiguration(@RequestParam UUID shiftId, @RequestParam UUID id) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<String>(configurationService.deleteShiftConfiguration(shiftId, id), HttpStatus.OK);

		}catch(ConfigurationNotFoundException e){
			response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}

	@Operation(summary = "delete department configuration by id")
	@DeleteMapping("/delet dept config")
	public ResponseEntity<?> deleteDepartmentConfiguration(@RequestParam UUID id, @RequestParam UUID customId) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<String>(configurationService.deleteDepartmentConfiguration(id, customId), HttpStatus.OK);

		}catch(ConfigurationNotFoundException e){
			response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}



	@PutMapping
	@RosLogDebug
	@Operation(summary = "Editing the Restaurant Configuration")
	// Editing the Restaurant Configuration
	public ResponseEntity<?> editConfiguration(@RequestBody RestaurantConfigurationDto dto,
			@RequestParam("restaurantId") UUID restaurantId) {
		ResponseEntity<?> response;
		try {
			log.info("Updating Restaurant Configuration: {}", dto);
			response = new ResponseEntity<RestaurantConfigurationDto>(
					configurationService.updateConfiguration(dto, restaurantId), HttpStatus.OK);

		} catch (NullPointerException ex) {
			log.error(ex.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(ex.getMessage()), HttpStatus.OK);
		} catch (ConfigurationNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/loadrestconfig/{id}")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Load  Restaurant Configuration based on Restaurant Id")
	// Get Restaurant Configuration based on id
	public ResponseEntity<?> getConfigurationById(@PathVariable(value = "id") UUID id) {
		ResponseEntity<?> response;
		try {
			log.info("Getting Restaurant Confiuration by id : {}", id);
			response = new ResponseEntity<RestaurantConfigurationDto>(configurationService.findByResaurantId(id),
					HttpStatus.OK);
		} catch (ConfigurationNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/third-party")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Get Third Party List")
	// Get Third Party List
	public ResponseEntity<?> getAllThirdParty() {
		ResponseEntity<?> response;
		try {
			log.info("Get Third Party List");
			response = new ResponseEntity<List<String>>(configurationService.getAllThirdPartyList(),
					HttpStatus.OK);
		} catch (ConfigurationNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/petty-cash")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Get Third Party List")
	// Get Third Party List
	public ResponseEntity<?> getAllPettyCash() {
		ResponseEntity<?> response;
		try {
			log.info("Get Petty Cash List");
			response = new ResponseEntity<List<String>>(configurationService.getAllPettyCashTypeList(),
					HttpStatus.OK);
		} catch (ConfigurationNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/pdqs")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Get PDQ Machines List")
	// Get Third Party List
	public ResponseEntity<?> getAllPDQS() {
		ResponseEntity<?> response;
		try {
			log.info("Get PDQS List");
			response = new ResponseEntity<List<String>>(configurationService.getAllPDQMachines(), HttpStatus.OK);
		} catch (ConfigurationNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}
	
	
	@GetMapping("/pdqCards")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Get PDQ Machines List")
	// Get Third Party List
	public ResponseEntity<?> getAllPDQCardsS() {
		ResponseEntity<?> response;
		try {
			log.info("Get PDQS List");
			response = new ResponseEntity<List<String>>(configurationService.getAllPDQCards(), HttpStatus.OK);
		} catch (PDQCardNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/currency")

	@ResponseBody

	@RosLogDebug

	@Operation(summary = "Get CurrencyTypes List")


	public ResponseEntity<?> getAllCurrencyTypes() {

		ResponseEntity<?> response;

		try {

			log.info("Get Currency List");

			response = new ResponseEntity<List<CurrencyDto>>(configurationService.getAllCurrencyList(), HttpStatus.OK);

		} catch (ConfigurationNotFoundException e) {

			log.error(e.getMessage());

			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);

		}

		return response;

	}

	@PostMapping("/currency")

	@ResponseBody

	@RosLogDebug

	@Operation(summary = "Save CurrencyTypes List")

	public ResponseEntity<?> saveCurrencyList(@RequestBody List<CurrencyDto> currencyDtos) {

		ResponseEntity<?> response;

		try {

			log.info("Save Currency List");

			response = new ResponseEntity<List<CurrencyDto>>(configurationService.saveCurrencyList(currencyDtos), HttpStatus.OK);

		} catch (CurrencyAlreadyExistsException e) {

			log.error(e.getMessage());

			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);

		}

		return response;

	}
	
    @PostMapping("/pdqMachines/add")
    @Operation(summary = "Add PDQ machines")
    public ResponseEntity<?> addPdqMachines(@RequestBody List<PDQMachine> pdqMachines) throws PDQMachineNotFoundException {

 

        ResponseEntity<?> response = new ResponseEntity<List<PDQMachine>>(configurationService.addPdqMachines(pdqMachines),
                HttpStatus.OK);

 

        return response;

 

    }

 

    @PostMapping("/pdqCards/add")
    @Operation(summary = "Add PDQ card")
    public ResponseEntity<?> addPdqCard(@RequestBody List<PDQCard> pdqCards) throws PDQCardNotFoundException {
        ResponseEntity<?> response = new ResponseEntity<List<PDQCard>>(configurationService.addPdqCards(pdqCards),
                HttpStatus.OK);

 

        return response;

 

    }

 

    @PostMapping("/thirdPartySources/add")
    @Operation(summary = "Add Third Party Source")

 

    public ResponseEntity<?> addThirdPartySource(@RequestBody List<ThirdPartySource> thirdPartySources)
            throws ThirdPartySourceNotFoundException {

 

        ResponseEntity<?> response = new ResponseEntity<List<ThirdPartySource>>(
                configurationService.addThirdPartySources(thirdPartySources), HttpStatus.OK);
        return response;
    }
	
}
