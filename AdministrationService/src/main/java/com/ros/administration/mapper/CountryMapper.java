package com.ros.administration.mapper;

import com.ros.administration.controller.dto.CountryDto;
import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.model.Country;
import com.ros.administration.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 *
 * This interface converts user entities to dtos
 *
 */
@Mapper
(componentModel="spring")
@Component

public interface CountryMapper {

    CountryMapper mapper = Mappers.getMapper(CountryMapper.class);


    //	@Mapping(target = "account.accountStatus",source = "account.accountStatus.status")

//    @Mapping(target = "code", source = "taxCountryCode")
//    @Mapping(target = "name", source = "countryName")
    CountryDto convertToCountryDto(Country country);

    //	@Mapping(target = "account.accountStatus.status",source = "account.accountStatus")
    Country convertToCountry(CountryDto countryDto);

    //	@Mapping(target ="account.accountStatus.status", source="account.accountStatus")
    void updateCountry(CountryDto countryDto, @MappingTarget Country country );


//    @Mapping(target = "code", source = "taxCountryCode")
//    @Mapping(target = "name", source = "countryName")
    List<CountryDto> convertToCountryDtoList(List<Country> countries);


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
    
//    @Mapping(target = "taxCountryCode", source = "code")
//    @Mapping(target = "countryName", source = "name")
	List<Country> convertToCountryList(List<CountryDto> countriesDto);
}
