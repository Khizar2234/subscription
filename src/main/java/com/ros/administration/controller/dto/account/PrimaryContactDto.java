package com.ros.administration.controller.dto.account;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import com.ros.administration.controller.dto.LineOfBusinessDto;
import com.ros.administration.controller.dto.RestaurantDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryContactDto {
	
	private UUID id;

	private String firstName;
	private String middleName;
	private String lastName;
	
	private String primaryContactEmail;
	
	private long primaryContactPhoneNo;
	
	private long primaryContactTelephoneNo;

}
