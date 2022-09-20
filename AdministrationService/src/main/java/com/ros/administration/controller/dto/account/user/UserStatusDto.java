package com.ros.administration.controller.dto.account.user;

import java.util.UUID;

import com.ros.administration.model.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatusDto {

	private UUID id;
	
	private String username;
	private UUID AzureUUID;
	private String azureUPN;
	private EStatus userStatus;
	
	private EStatus accountStatus;
	
	private EStatus accountSubscriptionStatus;
	
}
