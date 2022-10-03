package com.ros.administration.controller.dto.subscription;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionPackageSpecificationDto {
	
	private UUID id;
	private Integer noOfCashUpSheet;
	private Integer noOfRestaurant;
	private Integer noOfEmployee;
	private Integer userCount;
	private String storageLimit;

}
