package com.ros.administration.controller.dto.account.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDto {
	private UUID id;
	
	private String email;
	private String username;
	//to be removed
	private String code;
	private UUID AzureUUID;
	private String azureUPN;
	
	private RoleDto role;
	
	private EStatus userStatus;

	@Temporal(value = TemporalType.DATE)
	private Date createdDate;

	@Temporal(value = TemporalType.DATE)
	private Date lastModifiedDate;

	private String recordCreatedBy;

	private String modifiedBy;

	private UserProfileDto userProfile;

}
