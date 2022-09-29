package com.ros.administration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.ros.administration.controller.dto.CurrencyDto;
import com.ros.administration.controller.dto.configuration.CashUpIntervalDto;
import com.ros.administration.controller.dto.configuration.PDQTypeDto;
import com.ros.administration.controller.dto.configuration.PettyCashTypeDto;
import com.ros.administration.controller.dto.configuration.RestaurantConfigurationDto;
import com.ros.administration.controller.dto.configuration.ThirdPartyCategoryDto;
import com.ros.administration.controller.dto.configuration.ThirdPartyConfigDto;
import com.ros.administration.model.configuration.CashUpInterval;
import com.ros.administration.model.configuration.Currency;
import com.ros.administration.model.configuration.PDQType;
import com.ros.administration.model.configuration.PettyCashType;
import com.ros.administration.model.configuration.RestaurantConfiguration;
import com.ros.administration.model.configuration.ThirdPartyCategory;
import com.ros.administration.model.configuration.ThirdPartyConfig;

/**
 * 
 * This interface represents the converting entities and dtos
 *
 */
@Mapper
@Component
public interface RestaurantConfigurationMapper {

	RestaurantConfigurationMapper mapper = Mappers.getMapper(RestaurantConfigurationMapper.class);
	
	@Mapping(target = "currency.symbol",source="currency.symbol")
	RestaurantConfiguration convertToRestaurantConfigEntity(RestaurantConfigurationDto dto);
	@Mapping(target = "currency",source="currency")
	RestaurantConfigurationDto convertToRestaurantConfigDto(RestaurantConfiguration entity);

//	@Mapping(source = "note.reason", target = "reason")
//	@Mapping(source = "note.reasonAddedBy", target = "reasonAddedBy")
	void updateRestaurantConfig(RestaurantConfigurationDto dto, @MappingTarget RestaurantConfiguration configuration);

	// Complaint convertToComplaitEntity(ComplaintDto dto);

	// ComplaintDto convertToComplaiDto(Complaint dto);

//	@Mapping(target = "KPITotal", source = "KPITotal")
//	@Mapping(target = "delivery", source = "deliverytotal")
//	@Mapping(target = "PDQ", source = "PDQtotal")
//	@Mapping(target = "CASH", source = "CASHtotal")
//	@Mapping(target = "EPOS", source = "EPOStotal")

	PDQType convertToPettyCashEntity(PDQTypeDto dto);

	ThirdPartyConfig convertToThirdPartyEntity(ThirdPartyConfigDto dto);

	PDQTypeDto convertToPettyCashDto(PDQType pdqType);

	ThirdPartyConfigDto convertToThirdPartyDto(ThirdPartyConfig config);

	@Mapping(target = "eInterval", source = "EInterval")
	CashUpIntervalDto convertCashUpIntervalToDto(CashUpInterval cashUpInterval);

	@Mapping(target = "eInterval", source = "EInterval")
	CashUpInterval convertCashUpIntervalDtoToEntity(CashUpIntervalDto cashUpIntervalDto);

	ThirdPartyCategory convertToThirdPartyCategoryEntity(ThirdPartyCategoryDto dto);

	ThirdPartyCategoryDto convertToThirdPartyCategoryDto(ThirdPartyCategory entity);

	PettyCashType convertToPettyCashTypeEntity(PettyCashTypeDto dto);

	PettyCashTypeDto convertToPettyCashTypeDto(PettyCashType entity);
	
	CurrencyDto convertToCurrencyDto(Currency currency);
	
	Currency convertToCurrency(CurrencyDto currencyDto);

}




