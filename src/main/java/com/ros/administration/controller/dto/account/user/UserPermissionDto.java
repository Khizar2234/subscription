package com.ros.administration.controller.dto.account.user;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserPermissionDto {
	private UUID id;

	private String featureCode;

	private String subscriptionCode;
	
	private String azureUPN;

	private boolean permitted;
}
