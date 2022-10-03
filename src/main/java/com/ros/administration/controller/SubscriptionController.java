package com.ros.administration.controller;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ros.administration.controller.dto.ProductDto;
import com.ros.administration.controller.dto.account.AccountSubscriptionDto;
import com.ros.administration.controller.dto.account.AccountSubscriptionDto2;
import com.ros.administration.controller.dto.subscription.SubscriptionDto;
import com.ros.administration.exceptions.SubscriptionAlreadyExistsException;
import com.ros.administration.exceptions.SubscriptionNotFoundException;
import com.ros.administration.model.Restaurant;
import com.ros.administration.service.RestaurantService;
import com.ros.administration.service.SubscriptionService;
import com.ros.administration.service.UserService;
import com.ros.administration.service.impl.SubscriptionServiceImpl;
import com.ros.administration.util.Properties;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/subscription")
@CrossOrigin("*")
@Slf4j
public class SubscriptionController {
	
	@Autowired
	private SubscriptionServiceImpl subscriptionService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private UserService userService;
	
	@Operation(summary = "add subcription")
	@PostMapping("/add")
	public ResponseEntity<?> addSubcription(@RequestBody SubscriptionDto subscriptionDto){
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<SubscriptionDto>(subscriptionService.addSubscription(subscriptionDto), HttpStatus.OK);
		} catch(SubscriptionAlreadyExistsException e) {
			e.printStackTrace();
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}
	
	
	@Operation(summary = "Edit Subscription")
	@PutMapping("/editSubscription")
	public ResponseEntity<?> editSubscription(@RequestBody SubscriptionDto subscriptionDto){
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<SubscriptionDto>(subscriptionService.editSubscription(subscriptionDto), HttpStatus.OK);
		} catch (SubscriptionNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@Operation(summary = "get subcription frequency")
	@GetMapping("/subcriptionFrequency")
	public ResponseEntity<?> getSubcriptionFrequency(){
		ResponseEntity<?> response;
		response = new ResponseEntity<List<String>>(subscriptionService.getSubscriptionFrequency(), HttpStatus.OK);
		return response;
	}
	
	@Operation(summary = "get subcription name")
	@GetMapping("/subcriptionName")
	public ResponseEntity<?> getSubcriptionName(){
		ResponseEntity<?> response;
		response = new ResponseEntity<List<String>>(subscriptionService.getSubscriptionName(), HttpStatus.OK);
		return response;
	}
	
	@Operation(summary = "get subcription pricing Type")
	@GetMapping("/subcriptionPricingType")
	public ResponseEntity<?> getSubcriptionPricingType(){
		ResponseEntity<?> response;
		response = new ResponseEntity<List<String>>(subscriptionService.getSubscriptionPricingType(), HttpStatus.OK);
		return response;
	}
	
	@Operation(summary = "get all subcriptions")
	@GetMapping("/getAllSubscription")
	public ResponseEntity<?> getAllSubscriptions(){
		ResponseEntity<?> response;
		response = new ResponseEntity<List<SubscriptionDto>>(subscriptionService.getAllSubscriptions(), HttpStatus.OK);
		return response;
	}
	
	@Operation(summary = "get subcription By Id")
	@GetMapping("/getSubscriptionById")
	public ResponseEntity<?> getSubscriptionById(@RequestParam UUID id){
		ResponseEntity<?> response;
		response = new ResponseEntity<SubscriptionDto>(subscriptionService.getSubscriptionById(id), HttpStatus.OK);
		return response;
	}
	
	@Operation(summary = "activite or deactivate Subscription")
	@PutMapping("/updateActiveOrDeactiveSubscription")
	public ResponseEntity<?> updateActiveOrDeactiveSubscription(@RequestBody SubscriptionDto subscriptionDto){
		ResponseEntity<?> response = new ResponseEntity<String>(subscriptionService.updateActiveOrDeactiveSubscription(subscriptionDto), HttpStatus.OK);
		return response;
	}

	@Operation(summary = "set all subsciption features")
	@PutMapping("/setAllSubFeatures")
	public ResponseEntity<?> setAllSubscriptionFeatures(@RequestParam String subscriptionCode){
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<String>(subscriptionService.setAllSubscriptionFeatures(subscriptionCode), HttpStatus.OK);
		} catch (SubscriptionNotFoundException e) {
			response = new ResponseEntity<String>(Properties.subscriptionNotFound, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@Operation(summary = "Configure Subscription Features")
	@PutMapping("/configure")
	public ResponseEntity<?> configureSubscription(@RequestBody SubscriptionDto subscriptionDto){
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<SubscriptionDto>(subscriptionService.configureSubscription(subscriptionDto), HttpStatus.OK);
		} catch (SubscriptionNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@Operation(summary = "add Product")
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto){
		ResponseEntity<?> response = new ResponseEntity<ProductDto>(subscriptionService.addProduct(productDto), HttpStatus.OK);
		return response;
	}
	
	@Operation(summary = "get all Product names")
	@GetMapping("/getAllProductNames")
	public ResponseEntity<?> getAllProducts(){
		ResponseEntity<?> response;
		response = new ResponseEntity<List<String>>(subscriptionService.getAllProductName(), HttpStatus.OK);
		return response;
	}

	
	@Operation(summary = "get subscription by product code")
	@GetMapping("/getSubscriptionByProductCode")
	public ResponseEntity<?> getSubscriptionByProductCode(@RequestParam String productCode){
		ResponseEntity<?> response;
		response = new ResponseEntity<List<String>>(subscriptionService.getSubscriptionByProductCode(productCode), HttpStatus.OK);
		return response;
	}
	
	@Operation(summary = "get subscription by subscription code")
	@GetMapping("/getSubscriptionCodeBySubscriptionCode")
	public ResponseEntity<?> getSubscriptionBySubscriptionCode(@RequestParam String subscriptionCode){
		ResponseEntity<?> response;
		response = new ResponseEntity<SubscriptionDto>(subscriptionService.getSubscriptionBySubscriptionCode(subscriptionCode), HttpStatus.OK);
		return response;
	}
	
	@Operation(summary = "get all Product Codes")
	@GetMapping("/getAllProductCodes")
    public ResponseEntity<?> getAllProductCodes(){
        ResponseEntity<?> response;
        response = new ResponseEntity<List<String>>(subscriptionService.getAllProductCode(), HttpStatus.OK);
        return response;    
    }
	
	@Operation(summary = "get subscription usage of a client ")
    @GetMapping("/getSubscriptionUsage")
    @ResponseBody
    public ResponseEntity<?> getSubscriptionUsage(@RequestParam UUID clientId){
        ResponseEntity<?> response;
        List<Restaurant> restaurants=restaurantService.getListOfRestaurants(clientId);
        int userCount=userService.totalCountOfUsersFromrestaurants(restaurants);
        int restaurantCount=restaurantService.getListOfRestaurants(clientId).size();
        int employeeCount=userService.totalEmployeesFromRestaurants(restaurants);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("userCount", userCount);
        map.put("restaurantCount", restaurantCount);
        map.put("employeeCount", employeeCount);
        response=new ResponseEntity<HashMap<String, Integer>>(map,HttpStatus.OK);
        return response;
    }
	
	@PutMapping("/deactivateAccountSubscription")
    public ResponseEntity<?> updateActiveOrDeactiveAccountSubscription(
            @RequestBody AccountSubscriptionDto2 subscriptionDto) {
        ResponseEntity<?> response;
        //      try {
        response = new ResponseEntity<String>(
                subscriptionService.updateActiveOrDeactiveAccountSubscription(subscriptionDto), HttpStatus.OK);
        //      } catch (SubscriptionNotFoundException e) {
        //          response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        //      }
        return response;
    }

    @Operation(summary = "get account subcription By Id")
    @GetMapping("/getAccountSubscriptionById")
    public ResponseEntity<?> getAccountSubscriptionById(@RequestParam UUID id) {
        ResponseEntity<?> response;
        response = new ResponseEntity<AccountSubscriptionDto>(subscriptionService.getAccountSubscriptionById(id),
                HttpStatus.OK);
        return response;
    }
}
