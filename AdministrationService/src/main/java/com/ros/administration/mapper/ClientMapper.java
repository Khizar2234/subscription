package com.ros.administration.mapper;

import com.ros.administration.controller.dto.account.AccountSubscriptionDto;
import com.ros.administration.controller.dto.account.ClientAccountDto;
import com.ros.administration.controller.dto.account.ClientAccountSubscriptionDto;
import com.ros.administration.controller.dto.account.ClientAddDto;
import com.ros.administration.controller.dto.account.ClientDetailedInfoDto;
import com.ros.administration.controller.dto.account.ClientDto;
import com.ros.administration.model.Country;
import com.ros.administration.model.Feature;
import com.ros.administration.model.account.Account;
import com.ros.administration.model.account.Client;
import com.ros.administration.model.enums.EStatus;
import com.ros.administration.repository.FeatureRepository;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Mapper
@Component
public abstract class ClientMapper {

	@Autowired
	protected FeatureRepository featureRepository;


	@Mapping(target = "address.country.id", source = "address.country.id")
	@Mapping(target = "address.country.countryName", source = "address.country.countryName")
//	@Mapping(target ="addressDto",source = "address")
	@Mapping(target = "primaryContact", source = "primaryContact")
//	@Mapping(target = "accountStatus", source = "estatus")
	public abstract ClientDto convertToClientDto(Client client);

//	@Mapping(target = "accountStatus", source = "estatus")
//	public abstract ClientAccountDto convertToClientAccountDto(Client client);
	
//	@Mapping(target = "accountStatus", source = "estatus")
	public abstract ClientDetailedInfoDto convertToClientDetailedInfoDto(Client client);

	public abstract Client convertClientDetailedInfoDtoToClient(ClientDetailedInfoDto client);

//	@Mapping(target = "address.country.id", source = "address.countryId")
//	@Mapping(target = "address.country.countryName", source = "address.countryName")
//	@Mapping(target ="address",source = "addressDto")
	@Mapping(target = "primaryContact", source = "primaryContact")
	public abstract Client convertToCLient(ClientDto clientDto);

	@Mapping(target = "primaryContact", source = "primaryContact")
	public abstract Client convertToCLientAdd(ClientAddDto clientDto);

	@Mapping(target = "primaryContact", source = "primaryContact")
	public abstract ClientAddDto convertToCLientAddDto(Client client);

//	@Mapping(target ="address",source = "addressDto")
	public abstract void updateClient(ClientDto clientDto, @MappingTarget Client client);

//	@Mapping(target = "countryName", source = "address.country.countryName")
//	@Mapping(target = "countryId", source = "address.country.id")
//	@Mapping(target ="addressDto",source = "address")
	public abstract ClientDto convertToViewCLient(Client client);

	public abstract ClientAccountSubscriptionDto convertToAccountSubsciptionDto(Account account);
	
	String toString(Country country) {
		return country.getCountryName();
	}

	String toString(URI uri) {
		return uri.toString();

	}

	URI toURI(String string) {
		URI uri = null;
		try {
			uri = new URI(string);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uri;

	}

	String toString(Feature feature) {
		if (feature!=null && feature.getFeatureCode()!=null) {
		return feature.getFeatureCode();
		}
		else return "";
	}
	
	Feature toFeature(String featureCode) {
		Optional<Feature> feature=featureRepository.findByFeatureCode(featureCode);
		if(feature.isPresent()) {
			return feature.get();
		}else {
			return null;
		}
	}


}
