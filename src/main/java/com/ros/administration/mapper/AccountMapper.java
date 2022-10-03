package com.ros.administration.mapper;


import java.net.URI;
import java.net.URISyntaxException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.administration.controller.dto.account.AccountDto;
import com.ros.administration.controller.dto.account.ClientDto;
import com.ros.administration.model.Country;
import com.ros.administration.model.Feature;
import com.ros.administration.model.account.Account;


/**
 * 
 * This interface converts account entities to dtos
 *
 */
@Mapper
@Component
public interface AccountMapper {


	//@Mapping(target = "accountStatus",source = "accountStatus.status")
	AccountDto convertToAccountDto(Account account);

	default String toString(Feature feature) {

		if (feature!=null && feature.getFeatureCode()!=null) {
		return feature.getFeatureCode();
		}
		else return "";
	}


	default String toString(Country country) {
		return country.getCountryName();
	}
	
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
