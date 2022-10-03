package com.ros.administration.controller.dto.account.user;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ros.administration.controller.dto.account.AccountSubscriptionDto;
import com.ros.administration.controller.dto.account.RoleDto;
import com.ros.administration.controller.dto.account.UserAccountDto;
import com.ros.administration.controller.dto.account.UserAccountSubscriptionDto;
import com.ros.administration.model.account.Account;
import com.ros.administration.model.account.Role;
import com.ros.administration.model.account.UserPermission;
import com.ros.administration.model.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailedUserInfoDto {
	private UUID id;
	private String email;
	private UUID AzureUUID;
	private String azureUPN;
	private String username;
	private UserProfileDto userProfile;
	
	@Temporal(value = TemporalType.DATE)
	private Date createdDate;
	
	private RoleDto role;
	private EStatus userStatus;
	private List<UserPermissionDto> userPermissions;
	private UserAccountDto account;
	private UserAccountSubscriptionDto accountSubscription;
}
