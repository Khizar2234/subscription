package com.ros.administration.mapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.ros.administration.controller.dto.IntegrationDto;
import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.controller.dto.RestaurantIntegrationDto;
import com.ros.administration.model.Integration;
import com.ros.administration.model.Restaurant;
import com.ros.administration.model.RestaurantIntegration;

/**
 * 
 * This interface converts user entities to dtos
 *
 */
@Mapper
//(componentModel="spring")
@Component
public interface IntegrationMapper {
	IntegrationMapper mapper = Mappers.getMapper(IntegrationMapper.class);

	List<IntegrationDto> convertToIntegrationDtoList(List<Integration> integrations);

	default String toString(URI uri) {
		return uri.toString();

	}

	default URI toURI(String string) {
		URI uri = null;
		try {
			uri = new URI(string);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uri;

	}
	
	RestaurantIntegrationDto convertToRestaurantIntegrationDto(RestaurantIntegration restaurantIntegration);
	
	RestaurantIntegration convertToRestaurantIntegrationEntity(RestaurantIntegrationDto restaurantIntegrationDto);

	List<Integration> convertToIntegrationList(List<IntegrationDto> integrations);

	IntegrationDto convertToIntegrationDto(Integration integration);

	Integration convertToIntegration(IntegrationDto integrationDto);

	List<RestaurantIntegrationDto> convertToRestaurantIntegrationDtoList(
			List<RestaurantIntegration> restaurantIntegrations);

	List<RestaurantIntegration> convertToRestaurantIntegrationList(
			List<RestaurantIntegrationDto> restaurantIntegrationDtos);
}
