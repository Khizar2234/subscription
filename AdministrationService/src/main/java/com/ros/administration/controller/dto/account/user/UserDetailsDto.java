package com.ros.administration.controller.dto.account.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.controller.dto.account.ClientDto;
import com.ros.administration.controller.dto.account.RoleDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDetailsDto {
	
	private UUID id;
	private String email;
	private UUID AzureUUID;
	private String username;
	private String azureUPN;
	
	@Temporal(value = TemporalType.DATE)
	private Date createdDate;

	private String recordCreatedBy;

	private String modifiedBy;

	@Temporal(value = TemporalType.DATE)
	private Date lastModifiedDate;
	
	private UserProfileDto userProfile;
	private RoleDto role;
	private List<RestaurantDto> restaurants;
	private ClientDto client;


}
