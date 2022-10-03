package com.ros.administration.model.subscription.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubcriptionFrequency {
	
	private static SubcriptionFrequency frequency = null;
	
	private SubcriptionFrequency() {
		
	}
	
	public static SubcriptionFrequency getInstance() {
		if(frequency == null) {
			frequency = new SubcriptionFrequency();
		}
		return frequency;
	}
	
	public enum SubcriptionFrequencyEnum {
		MONTHLY, QUARTERLY, ANNUALLY, ONE_TIME
	}
	
	public List<String> getSubscriptionFrequency() {
		List<String> subscriptionFrequencyList = Stream.of(SubcriptionFrequencyEnum.values())
				.map(SubcriptionFrequencyEnum::name).collect(Collectors.toList());
		return subscriptionFrequencyList;
	}
}
