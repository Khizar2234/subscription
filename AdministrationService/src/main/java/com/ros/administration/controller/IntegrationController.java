package com.ros.administration.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AccountStatusRestaurantDetailsChecker;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.restaurantdetails.RestaurantDetails;
//import org.springframework.security.core.restaurantdetails.RestaurantnameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ros.administration.controller.dto.IntegrationDto;
import com.ros.administration.exceptions.IntegrationNotFoundException;
import com.ros.administration.exceptions.RestaurantAlreadyExistsException;
import com.ros.administration.exceptions.RestaurantIntegrationAlreadyExistsException;
import com.ros.administration.exceptions.RestaurantNotFoundException;
import com.ros.administration.service.IntegrationService;
import com.ros.administration.service.RestaurantService;
import com.ros.administration.util.ExceptionHandler;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/intergration")
@CrossOrigin("*")
@Slf4j
public class IntegrationController {
	@Autowired
	private IntegrationService integrationService;

	@Operation(summary = "Create integrations")
	@PostMapping("/add integration")
	public ResponseEntity<?> addIntegration(@RequestBody IntegrationDto integrationDto) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<IntegrationDto>(integrationService.saveIntegration(integrationDto),
					HttpStatus.OK);

		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			response = new ResponseEntity<NullPointerException>(new NullPointerException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IntegrationNotFoundException e) {
			response = new ResponseEntity<IntegrationNotFoundException>(
					new IntegrationNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Operation(summary = "get all integrations")
	@GetMapping("/allIntegrations")
	public ResponseEntity<?> getAllIntegrations() {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<List<IntegrationDto>>(integrationService.getAllIntegrations(), HttpStatus.OK);

		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("restaurant not found");
			response = new ResponseEntity<NullPointerException>(new NullPointerException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IntegrationNotFoundException e) {
			response = new ResponseEntity<IntegrationNotFoundException>(
					new IntegrationNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Operation(summary = "delete integration by id")
	@DeleteMapping
	public ResponseEntity<?> deleteIntegration(@RequestParam UUID id) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<Boolean>(integrationService.deleteIntegration(id), HttpStatus.OK);

		} catch (IntegrationNotFoundException e) {
			// TODO Auto-generated catch block
			response = new ResponseEntity<IntegrationNotFoundException>(
					new IntegrationNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
