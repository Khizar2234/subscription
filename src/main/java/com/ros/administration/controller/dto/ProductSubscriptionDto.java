package com.ros.administration.controller.dto;

import java.util.List;
import java.util.UUID;

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
public class ProductSubscriptionDto {

	private UUID id;
	private String name;
	private boolean subscriptionActive;
	private SubscriptionPackageSpecificationDto subscriptionPackageSpecification;
	private String description;
	private String subscriptionCode;
	private List<PricingDto> pricings;
}
