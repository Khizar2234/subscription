package com.ros.administration.controller.dto.account.user;

import java.util.List;
import java.util.UUID;

import com.ros.administration.controller.dto.ProductDto;
import com.ros.administration.controller.dto.subscription.PricingDto;
import com.ros.administration.controller.dto.subscription.SubscriptionFeatureDto;
import com.ros.administration.controller.dto.subscription.SubscriptionPackageSpecificationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscriptionDto {

	private UUID id;
	private String productCode;
	private String subscriptionCode;
}
