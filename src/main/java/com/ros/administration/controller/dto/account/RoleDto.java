package com.ros.administration.controller.dto.account;

import java.util.List;
import java.util.UUID;

import com.ros.administration.model.enums.ERole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
	private UUID id;
	
//alternative approach can be used
	private ERole name;
	
	private String code;
	
//	private List<PermissionDto> permissions;
}
