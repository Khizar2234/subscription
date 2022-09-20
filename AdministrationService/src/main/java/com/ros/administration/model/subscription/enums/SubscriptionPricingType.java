package com.ros.administration.model.subscription.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubscriptionPricingType {

	private static SubscriptionPricingType subscriptionPricingType = null;
	
	private SubscriptionPricingType() {
		
	}
	
	public static SubscriptionPricingType getInstance() {
		if(subscriptionPricingType == null) {
			subscriptionPricingType = new SubscriptionPricingType();
		}
		return subscriptionPricingType;
	}

	public enum SubscriptionPricingTypeEnum {
		PER_USER, PER_SETUP
	}
	
	public List<String> getSubscriptionPricingType() {
		List<String> subscriptionPricingTypeList = Stream.of(SubscriptionPricingTypeEnum.values())
				.map(SubscriptionPricingTypeEnum::name).collect(Collectors.toList());
		return subscriptionPricingTypeList;
	}
}
