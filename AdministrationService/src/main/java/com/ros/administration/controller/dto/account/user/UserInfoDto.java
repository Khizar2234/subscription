package com.ros.administration.controller.dto.account.user;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ros.administration.controller.dto.account.RoleDto;
import com.ros.administration.model.account.Account;
import com.ros.administration.model.account.AccountSubscription;
import com.ros.administration.model.account.Role;
import com.ros.administration.model.account.UserPermission;
import com.ros.administration.model.account.UserProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
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
	

}
