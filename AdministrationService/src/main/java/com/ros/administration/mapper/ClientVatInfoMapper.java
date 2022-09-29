package com.ros.administration.mapper;


import com.ros.administration.controller.dto.account.ClientVatInfoDto;
import com.ros.administration.model.account.ClientVatInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
public interface ClientVatInfoMapper {

    ContactInfoMapper mapper = Mappers.getMapper(ContactInfoMapper.class);


    //	@Mapping(target = "account.accountStatus",source = "account.accountStatus.status")
    // ClientVatInfoDto convertToClientVatInfoDto(ClientVatInfo clientVatInfo);

    //	@Mapping(target = "account.accountStatus.status",source = "account.accountStatus")

    ClientVatInfo convertToClientVatInfo(ClientVatInfoDto clientVatInfoDto);

    //	@Mapping(target ="account.accountStatus.status", source="account.accountStatus")
    void updateClientVatInfo(ClientVatInfoDto clientVatInfoDto, @MappingTarget ClientVatInfo clientVatInfo);


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
