package com.ros.administration.controller;

import com.ros.administration.controller.dto.CountryDto;
import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.exceptions.CountryAlreadyExistsException;
import com.ros.administration.exceptions.RestaurantNotFoundException;
import com.ros.administration.model.Country;
import com.ros.administration.service.CountryService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
@CrossOrigin("*")
@Slf4j
public class CountryController {
    @Autowired
    CountryService countryService;

    @Operation(summary = "get countries")
    @GetMapping("/list")
    public ResponseEntity<?> get() {

        ResponseEntity<?> response;
        response = new ResponseEntity<List<CountryDto>>(countryService.get(), HttpStatus.OK);
        return response;
    }
    
//    @Hidden
    @Operation(summary = "add all Countries")
	@PostMapping("/addAllCountries")
	public ResponseEntity<?> addAllCountries(@RequestBody List<CountryDto> countries){
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<List<CountryDto>>(countryService.addAllCountries(countries), HttpStatus.OK);
		} catch (CountryAlreadyExistsException e) {
			e.printStackTrace();
			response = new ResponseEntity<CountryAlreadyExistsException>(new CountryAlreadyExistsException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
