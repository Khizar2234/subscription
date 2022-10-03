package com.ros.administration.mapper;


import com.ros.administration.controller.dto.AddressDto;
import com.ros.administration.model.Address;
import com.ros.administration.model.Country;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 *
 * This interface converts user entities to dtos
 *
 */
@Mapper
//(componentModel="spring")
@Component
public abstract class AddressMapper {

//    AddressMapper mapper = Mappers.getMapper(AddressMapper.class);


    //	@Mapping(target = "account.accountStatus",source = "account.accountStatus.status")
    public abstract AddressDto convertToAddressDto(Address address);

    //	@Mapping(target = "account.accountStatus.status",source = "account.accountStatus")
    public abstract Address convertToAddress(AddressDto addressDto);

    //	@Mapping(target ="account.accountStatus.status", source="account.accountStatus")
    public abstract void updateAddress(AddressDto addressDto, @MappingTarget Address address);

//    public abstract Country toCountry(UUID countryID, String countryName) {
//    		
//    }
    
//    public Country toCountry()
    

    public String toString(URI uri) {
        return uri.toString();

    }

    public URI toURI(String string) {
        URI uri = null;
        try {
            uri = new URI(string);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return uri;

    }
}
