package com.ros.administration.controller.dto.subscription;

import java.util.UUID;

import com.ros.administration.controller.dto.FeatureDto;
import com.ros.administration.controller.dto.account.user.UserPermissionDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionFeatureDto {

	private UUID id;
	
	private String feature;
	
	private boolean subscriptionFeatureActive;
}
