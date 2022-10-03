package com.ros.administration.model.subscription.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubscriptionName {
	private static SubscriptionName subscriptionName = null;
	
	private SubscriptionName() {
		
	}
	
	public static SubscriptionName getInstance() {
		if(subscriptionName == null) {
			subscriptionName = new SubscriptionName();
		}
		return subscriptionName;
	}
	
	public enum SubscriptionNameEnum {
		FREE, FREMIUM, PREMIUM
	}
	
	public List<String> getSubscriptionName() {
		List<String> subscriptionNameList = Stream.of(SubscriptionNameEnum.values())
				.map(SubscriptionNameEnum::name).collect(Collectors.toList());
		return subscriptionNameList;
	}
}
