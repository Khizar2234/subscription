package com.ros.administration.mapper;

import com.ros.administration.controller.dto.account.ContactInfoDto;
import com.ros.administration.model.account.ContactInfo;
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
public interface ContactInfoMapper {

    ContactInfoMapper mapper = Mappers.getMapper(ContactInfoMapper.class);


    //	@Mapping(target = "account.accountStatus",source = "account.accountStatus.status")
    ContactInfoDto convertToContactInfoDto(ContactInfo contactInfo);

    //	@Mapping(target = "account.accountStatus.status",source = "account.accountStatus")
    ContactInfo convertToContactInfo(ContactInfoDto contactInfoDto);

    //	@Mapping(target ="account.accountStatus.status", source="account.accountStatus")
    void updateContactInfo(ContactInfoDto contactInfoDto, @MappingTarget ContactInfo contactInfo);


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
