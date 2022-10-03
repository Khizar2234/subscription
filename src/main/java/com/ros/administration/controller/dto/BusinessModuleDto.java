package com.ros.administration.controller.dto;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;

import com.ros.administration.model.FunctionalModule;
import com.ros.administration.model.enums.PermissionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessModuleDto {

	private UUID id;

	private String name;

	private String code;

	private String displayName;

	private List<FunctionalModuleDto> functionalModules;
	
}
