package com.ros.administration.controller.dto.account.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.controller.dto.account.AccountSubscriptionDto;
import com.ros.administration.controller.dto.account.RoleDto;
import com.ros.administration.controller.dto.account.UserAccountDto;
import com.ros.administration.controller.dto.account.UserAccountSubscriptionDto;
import com.ros.administration.model.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private UUID id;
	
	private String email;
	private String username;
	//to be removed
	private String code;
	private UUID AzureUUID;
	
	private String azureUPN;
	
	private RoleDto role;
	
	private EStatus userStatus;
	
	private UserProfileDto userProfile;

	private UserAccountDto account;
	
	@Temporal(value = TemporalType.DATE)
	private Date createdDate;

	@Temporal(value = TemporalType.DATE)
	private Date lastModifiedDate;

	private String recordCreatedBy;

	private String modifiedBy;


	//private List<UserPermissionDto> userPermissions;

	private UserAccountSubscriptionDto accountSubscription;	
}
