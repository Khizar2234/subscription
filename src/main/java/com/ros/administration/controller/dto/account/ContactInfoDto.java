package com.ros.administration.controller.dto.account;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoDto {
	private UUID id;

	
	private String contactEmail;
	
	private long contactPhoneNo;
	
	private long contactTelephoneNo;
	
	private long faxNo;
	
	private String websiteURL;

}
