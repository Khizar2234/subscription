package com.ros.administration.controller.dto;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ros.administration.controller.dto.subscription.SubscriptionDto;
import com.ros.administration.model.BusinessModule;
import com.ros.administration.model.Product;
import com.ros.administration.model.subscription.Subscription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	private UUID id;

	private String productName;

	private List<BusinessModuleDto> businessModules;

	private List<ProductSubscriptionDto> subscriptions;

	private String productCode;
	
	private String displayName;

}
