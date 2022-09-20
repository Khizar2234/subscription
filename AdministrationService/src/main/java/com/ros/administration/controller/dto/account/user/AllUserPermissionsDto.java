package com.ros.administration.controller.dto.account.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ros.administration.controller.dto.account.RoleDto;
import com.ros.administration.model.account.Account;
import com.ros.administration.model.account.Role;
import com.ros.administration.model.account.UserPermission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllUserPermissionsDto {
	private UUID id;
	private String email;
	private String username;
	private UUID AzureUUID;
	private String azureUPN;
	private List<UserPermissionDto> userPermissions;
	private RoleDto role;

}
