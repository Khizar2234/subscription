package com.ros.administration.mapper;


import com.ros.administration.controller.dto.account.PrimaryContactDto;
import com.ros.administration.model.account.PrimaryContact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * This interface converts user entities to dtos
 *
 */
@Mapper
(componentModel="spring")
@Component

public interface PrimaryContactMapper {

    PrimaryContactMapper mapper = Mappers.getMapper(PrimaryContactMapper.class);


    //	@Mapping(target = "account.accountStatus",source = "account.accountStatus.status")
    PrimaryContactDto convertToPrimaryContactDto(PrimaryContact primaryContact);

    //	@Mapping(target = "account.accountStatus.status",source = "account.accountStatus")
    PrimaryContact convertToPrimaryContact(PrimaryContactDto primaryContactDto);

    //	@Mapping(target ="account.accountStatus.status", source="account.accountStatus")
    void updatePrimaryContact(PrimaryContactDto primaryContactDto, @MappingTarget PrimaryContact primaryContact);


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
}
