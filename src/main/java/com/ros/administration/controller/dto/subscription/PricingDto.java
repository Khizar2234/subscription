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
public class PricingDto {

	private UUID id;
	private String frequency;
	private String pricingType;
	private double cost;
}
