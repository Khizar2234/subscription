package com.ros.administration.controller.dto.configuration;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ros.administration.controller.dto.CurrencyDto;
import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.model.Restaurant;
import com.ros.administration.model.configuration.Currency;
import com.ros.administration.model.configuration.CurrencyType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantConfigurationDto {

	private UUID id;

	private List<CashUpIntervalDto> cashUpIntervals;

	private CurrencyDto currency;

	private CashnPDQConfigDto cashnPdqConfig;

	private ThirdPartyConfigDto thirdPartyConfig;
}
