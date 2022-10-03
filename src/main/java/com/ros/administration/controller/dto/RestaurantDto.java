package com.ros.administration.controller.dto;

import com.ros.administration.controller.dto.account.ClientDto;
import com.ros.administration.controller.dto.account.ContactInfoDto;
import com.ros.administration.controller.dto.account.ClientVatInfoDto;
import com.ros.administration.controller.dto.account.PrimaryContactDto;
import com.ros.administration.model.account.Client;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
	private UUID id;

	private String name;

	private String legalName;

	private String ownerName;

	private String ownerEmail;

	private UUID clientId;
	
	private Boolean tabletValidation;

	private String vatEditableCountryId;

	private String physicalId;

	private String postalId;

	private String postalAddressCountry;

	private String physicalAddressCountry;

	private String postalTaxCode;

	private String physicalTaxCode;

	private String vatCountryCode;

	private String vatCountryName;

	private String clientLegalName;
	
	private String clientBusinessName;

	private AddressDto postalAddress;

	private AddressDto physicalAddress;

	private PrimaryContactDto primaryContact;

	private ContactInfoDto contactInformation;

	private UUID vatCountryId;

	private String taxRegistrationNo;

	private ClientVatInfoDto vatInfo;


//	private List<AddressDto> addresses;

	private List<DepartmentDto> departments;

	private List<RestaurantIntegrationDto> integrations;
}
