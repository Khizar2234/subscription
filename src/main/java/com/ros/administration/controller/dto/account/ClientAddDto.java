package com.ros.administration.controller.dto.account;

import java.util.UUID;

import com.ros.administration.controller.dto.AddressDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientAddDto {
	private UUID id;

	private String azureUPN;

	private String name;

	private String legalName;

	private AddressDto address;

	private PrimaryContactDto primaryContact;
	
	private String clientPin;

}
