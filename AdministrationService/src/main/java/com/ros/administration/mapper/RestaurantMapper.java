package com.ros.administration.mapper;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.ros.administration.model.account.ClientVatInfo;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.ros.administration.controller.dto.IntegrationDto;
import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.model.Integration;
import com.ros.administration.model.Restaurant;



/**
 *
 * This interface converts user entities to dtos
 *
 */
@Mapper(collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
//(componentModel="spring")
@Component
public interface RestaurantMapper {
	RestaurantMapper mapper = Mappers.getMapper(RestaurantMapper.class);

	//	@Mapping(target = "account.accountStatus",source = "account.accountStatus.status")
	@Mapping(target = "vatCountryId", source = "vatInfo.country.id")
	@Mapping(target = "postalAddress.country.id", source = "postalAddress.country.id")
	@Mapping(target = "postalAddress.country.countryName", source = "postalAddress.country.countryName")
	@Mapping(target = "postalAddress.country.taxCountryCode", source = "postalAddress.country.taxCountryCode")
	@Mapping(target = "physicalAddress.country.id", source = "physicalAddress.country.id")
	@Mapping(target = "physicalAddress.country.countryName", source = "physicalAddress.country.countryName")
	@Mapping(target = "taxRegistrationNo", source = "vatInfo.taxRegistrationNo")
	@Mapping(target = "vatInfo.countryDto.id", source = "vatInfo.country.id")
	@Mapping(target = "vatInfo.countryDto.countryName", source = "vatInfo.country.countryName")
	@Mapping(target = "clientId", source = "client.id")
	@Mapping(target="integrations",source="restaurantIntegrations")
	@Mapping(target = "vatCountryName", source = "vatCountryName")
	@Mapping(target = "vatCountryCode", source = "vatCountryCode")
	RestaurantDto convertToRestaurantDto(Restaurant user);

	//	@Mapping(target = "account.accountStatus.status",source = "account.accountStatus")
//	@Mapping(target = "vatInfo.country.id", source = "vatInfo.countryDto.id")
//	@Mapping(target = "vatInfo.country.countryName", source = "vatInfo.countryDto.countryName")
//	@Mapping(target = "vatInfo.country.taxCountryCode", source = "vatInfo.countryDto.taxCountryCode")
//	@Mapping(target = "restaurantIntegrations", source="integrations")
//	@Mapping(target = "postalAddress.country.countryName", source = "postalAddress.country.countryName")
//	@Mapping(target = "postalAddress.country.id", source = "postalAddress.country.id")
//	@Mapping(target = "postalAddress.country.taxCountryCode", source = "postalAddress.country.taxCountryCode")
//	@Mapping(target = "client", source = "client")
	//@Mapping(target = "updatedBy", source = "")
	//@Mapping(target = "lastModifiedDate", source = "")
	//@Mapping(target = "createdDate", source = "")
	//@Mapping(target = "createdBy", source = "")
	//@Mapping(target = "client", source = "clientId")
	Restaurant convertToRestaurant(RestaurantDto userDto);

	//	@Mapping(target ="account.accountStatus.status", source="account.accountStatus")
	@Mapping(target = "vatInfo", source = "vatInfo")
	@Mapping(target = "vatInfo.country.id", source = "vatInfo.countryDto.id")
	@Mapping(target = "vatInfo.country.countryName", source = "vatInfo.countryDto.countryName")
	@Mapping(target = "vatInfo.country.taxCountryCode", source = "vatInfo.countryDto.taxCountryCode")
	@Mapping(target = "vatInfo.taxRegistrationNo", source = "taxRegistrationNo")
	@Mapping(target = "vatCountryName", source = "vatCountryName")
	@Mapping(target = "vatCountryCode", source = "vatCountryCode")
//	@Mapping(target = "client.id", source = "clientId")
////	@Mapping(target = "postalAddress", ignore = true)
	void updateRestaurant(RestaurantDto restaurantDto,@MappingTarget Restaurant restaurant);


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


	List<RestaurantDto> convertToRestaurantDtoList(List<Restaurant> restaurant);
}
