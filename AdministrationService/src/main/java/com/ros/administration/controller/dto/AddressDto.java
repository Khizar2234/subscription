package com.ros.administration.controller.dto;

import java.util.List;
import java.util.UUID;

import com.ros.administration.model.Country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
	
	private UUID id; 
	
	private String city;
	
	private String state;
	
	private String addressLine1;

	private String addressLine2;
	
	private String zipCode;
	
//	private UUID countryId;
//
//	private String countryName;
	
//	private UUID countryId;
//
//	private String countryName;
	
	private CountryDto country;
}
