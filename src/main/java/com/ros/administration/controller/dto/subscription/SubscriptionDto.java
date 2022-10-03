package com.ros.administration.controller.dto.subscription;

import java.util.List;
import java.util.UUID;

import com.ros.administration.controller.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {

	private UUID id;
	private String name;
	private String productCode;
	private String subscriptionDuration;
	private boolean subscriptionActive;
	private List<SubscriptionFeatureDto> subscriptionFeatures;
	private SubscriptionPackageSpecificationDto subscriptionPackageSpecification;
	private String description;
	private String subscriptionCode;
	private List<PricingDto> pricings;
}
