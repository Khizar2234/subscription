package com.ros.administration.controller.dto;

import java.util.List;
import java.util.UUID;

import com.ros.administration.model.Country;
import com.ros.administration.model.configuration.CurrencySymbol;
import com.ros.administration.model.configuration.CurrencyType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto {
	
	private UUID id;

	private CurrencyType currencyType;
	
	private CurrencySymbol symbol;
}
