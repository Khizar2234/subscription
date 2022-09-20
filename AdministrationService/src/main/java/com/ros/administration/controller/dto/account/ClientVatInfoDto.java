package com.ros.administration.controller.dto.account;

import java.util.UUID;

import com.ros.administration.controller.dto.CountryDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientVatInfoDto {

	private UUID id;

	private CountryDto countryDto;
	
	private String taxRegistrationNo;
}
