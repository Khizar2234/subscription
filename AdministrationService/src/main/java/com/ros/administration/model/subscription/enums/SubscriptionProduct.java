package com.ros.administration.model.subscription.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubscriptionProduct {

	private static SubscriptionProduct subscriptionProduct = null;
	
	private SubscriptionProduct() {
		
	}
	
	public static SubscriptionProduct getInstance() {
		if(subscriptionProduct == null) {
			subscriptionProduct = new SubscriptionProduct();
		}
		return subscriptionProduct;
	}

	public enum SubscriptionProductEnum {
		ERP, HRMS
	}
	
	public List<String> getSubscriptionProduct() {
		List<String> subscriptionProductList = Stream.of(SubscriptionProductEnum.values())
				.map(SubscriptionProductEnum::name).collect(Collectors.toList());
		return subscriptionProductList;
	}
}
