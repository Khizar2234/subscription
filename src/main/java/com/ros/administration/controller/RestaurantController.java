package com.ros.administration.controller;

import com.ros.administration.controller.dto.IntegrationDto;
import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.controller.dto.RestaurantIntegrationDto;
import com.ros.administration.exceptions.ClientNotFoundException;
import com.ros.administration.exceptions.ErrorHandler;
import com.ros.administration.exceptions.IntegrationNotFoundException;
import com.ros.administration.exceptions.RestaurantAlreadyExistsException;
import com.ros.administration.exceptions.RestaurantIntegrationAlreadyExistsException;
import com.ros.administration.exceptions.RestaurantNotFoundException;
import com.ros.administration.service.RestaurantService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin("*")
@Slf4j
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;


	@Operation(summary = "Add New Restaurant")
	@PostMapping
	public ResponseEntity<?> addRestaurant(@RequestBody RestaurantDto restaurantDto){
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(restaurantService.addRestaurant(restaurantDto), HttpStatus.OK);

		} catch (RestaurantAlreadyExistsException e) {
			// TODO Auto-generated catch block
			response = new ResponseEntity<RestaurantNotFoundException>(new RestaurantNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Operation(summary = "update restaurant details")
	@PutMapping
	public ResponseEntity<?> updateRestaurant(@RequestBody RestaurantDto restaurantDto){
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<RestaurantDto>(restaurantService.editRestaurant(restaurantDto), HttpStatus.OK);
			
		} catch (RestaurantNotFoundException e) {
			System.out.println("restaurant not found");
			response = new ResponseEntity<RestaurantNotFoundException>(new RestaurantNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
//	@Operation(summary = "update restaurant configuration")
//	@PutMapping
//	public ResponseEntity<?> updateRestaurantConfiguration(@RequestBody RestaurantDto restaurantDto){
//		ResponseEntity<?> response;
//		try {
//			response = new ResponseEntity<RestaurantDto>(restaurantService.editRestaurant(restaurantDto), HttpStatus.OK);
//			
//		} catch (RestaurantNotFoundException e) {
//			// TODO Auto-generated catch block
//			System.out.println("restaurant not found");
//			response = new ResponseEntity<RestaurantNotFoundException>(new RestaurantNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		return response;
//	}

	@Operation(summary = "get restaurant integrations")
	@GetMapping("/restaurantIntegrations")
	public ResponseEntity<?> getRestaurantIntegrations(@RequestParam UUID restaurantId){
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<List<RestaurantIntegrationDto>>(restaurantService.getRestaurantIntegrations(restaurantId), HttpStatus.OK);
			
		} catch (RestaurantNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("restaurant not found");
			response = new ResponseEntity<RestaurantNotFoundException>(new RestaurantNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@Operation(summary = "edit resturant integration status with id and integrationStatus")
	@PutMapping("/editResturantIntegration")
	public ResponseEntity<?> editIntegrationStatus(@RequestParam UUID id, @RequestParam boolean integrationStatus,@RequestParam String integrationCredentials) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<RestaurantIntegrationDto>(
					restaurantService.editIntegrationStatus(id, integrationStatus,integrationCredentials), HttpStatus.OK);

		} catch (IntegrationNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("restaurant not found");
			response = new ResponseEntity<IntegrationNotFoundException>(
					new IntegrationNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@Operation(summary = "add restaurant integrations")
	@PostMapping("/restaurantIntegrations")
	public ResponseEntity<?> addRestaurantIntegrations(@RequestBody RestaurantDto restaurantDto){
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<RestaurantDto>(restaurantService.addRestaurantIntegrations(restaurantDto), HttpStatus.OK);

		} catch (RestaurantIntegrationAlreadyExistsException e) {
			// TODO Auto-generated catch block
			System.out.println("restaurant not found");
			response = new ResponseEntity<RestaurantIntegrationAlreadyExistsException>(new RestaurantIntegrationAlreadyExistsException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Operation(summary = "get restaurant details by client Id")
	@GetMapping("/restaurants")
	public ResponseEntity<?> getRestaurantDetails(@RequestParam UUID clientId) {

		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<List<RestaurantDto>>(restaurantService.getRestaurantDetails(clientId), HttpStatus.OK);
		} catch (RestaurantNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("restaurant not found");
			response = new ResponseEntity<RestaurantNotFoundException>(new RestaurantNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Operation(summary = "get all restaurant details")
	@GetMapping("/restaurants/list")
	public ResponseEntity<?> getRestaurants() {

		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<List<RestaurantDto>>(restaurantService.getRestaurantDetails(), HttpStatus.OK);
		} catch (RestaurantNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("restaurant not found");
			response = new ResponseEntity<RestaurantNotFoundException>(new RestaurantNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@Operation(summary = "get all restaurant details pagewise")
	@GetMapping("/allrestaurants")
	public ResponseEntity<?> getAllRestaurants(int limit, int pageNo) {

		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<List<RestaurantDto>>(restaurantService.getAllRestaurants(limit,pageNo), HttpStatus.OK);
		} catch (RestaurantNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("restaurant not found");
			response = new ResponseEntity<RestaurantNotFoundException>(new RestaurantNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Operation(summary = "get restaurant details")
	@GetMapping("/{restaurantId}")
	public ResponseEntity<?> getRestaurantById(@PathVariable UUID restaurantId) {

		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(restaurantService.getRestaurantById(restaurantId), HttpStatus.OK);
		} catch (RestaurantNotFoundException e) {
			System.out.println("restaurant not found");
			response = new ResponseEntity<>(new RestaurantNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@Operation(summary = "add restaurant to user")
    @PostMapping("/addRestaurantToUser")
    public ResponseEntity<?> addRestaurantsToUser(@RequestParam UUID userId, @RequestBody List<UUID> restaurantIds){
        ResponseEntity<?> response;
        try {
          response = new ResponseEntity<String>(restaurantService.addRestaurantsToUser(userId, restaurantIds), HttpStatus.OK);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<String>("Restaurant could not be added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
	
	
	@Operation(summary = "update restaurant and user connections")
    @PutMapping("/addRestaurantToUser")
    public ResponseEntity<?> updateUserRestaurantConnections(@RequestParam UUID userId, @RequestBody List<UUID> restaurantIds){
        ResponseEntity<?> response;
        try {
          response = new ResponseEntity<String>(restaurantService.updateUserRestaurantConnections(userId, restaurantIds), HttpStatus.OK);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<String>("Restaurant could not be added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
	
	@Operation(summary = "delete restaurant")
	@DeleteMapping
	public ResponseEntity<?> deleteRestaurant(@RequestParam UUID restaurantId){
		
		ResponseEntity<?> response;
		
		try {
			
			response = new ResponseEntity<String>(restaurantService.deleteRestaurant(restaurantId), HttpStatus.OK);

		} catch (RestaurantNotFoundException e) {
			
			response = new ResponseEntity<RestaurantNotFoundException>(new RestaurantNotFoundException(e.getMessage()), HttpStatus.BAD_REQUEST);
		
		}
		
		return response;
	}
	
	@Operation(summary = "set Restaurant Pin")
	@PatchMapping("/setRestaurantPin")
	@ResponseBody
	public ResponseEntity<?> addROSTeamPin(@RequestParam UUID clientId,@RequestParam UUID restaurantId,@RequestParam String pin) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<Object>(restaurantService.setRestaurantPin(clientId, restaurantId, pin), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>( new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}
	
	@Operation(summary = "edit Restaurant Pin")
	@PatchMapping("/editRestaurantPin")
	@ResponseBody
	public ResponseEntity<?> editRestaurantPin(@RequestParam UUID clientId,@RequestParam UUID restaurantId,@RequestParam String oldPin, @RequestParam String newPin) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<Object>(restaurantService.editRestaurantPin(clientId, restaurantId, oldPin, newPin), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>( new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}
	
	@Operation(summary = "Verify Restaurant Pin")
	@GetMapping("/verifyRestaurantPin")
	@ResponseBody
	public ResponseEntity<?> verifyRestaurantPin(@RequestParam UUID restaurantId,@RequestParam String pin) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<Object>(restaurantService.verifyRestaurantPin(restaurantId,pin), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>( new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}
	
}
