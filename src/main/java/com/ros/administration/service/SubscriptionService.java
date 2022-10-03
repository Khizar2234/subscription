package com.ros.administration.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ros.administration.controller.dto.ProductDto;
import com.ros.administration.controller.dto.subscription.SubscriptionDto;
import com.ros.administration.exceptions.SubscriptionAlreadyExistsException;
import com.ros.administration.exceptions.SubscriptionNotFoundException;

@Service
public interface SubscriptionService {

	SubscriptionDto addSubscription(SubscriptionDto subsciptionDto) throws SubscriptionAlreadyExistsException;
	
	List<String> getSubscriptionFrequency();
	
	List<String> getSubscriptionName();
	
	List<String> getSubscriptionPricingType();
	
	List<SubscriptionDto> getAllSubscriptions();
	
	SubscriptionDto getSubscriptionById(UUID id);
	
	String updateActiveOrDeactiveSubscription(SubscriptionDto subsciptionDto);
	
	ProductDto addProduct(ProductDto productDto);
	
	List<String> getAllProductName();

	SubscriptionDto configureSubscription(SubscriptionDto subscriptionDto) throws SubscriptionNotFoundException;

	SubscriptionDto editSubscription(SubscriptionDto subsciptionDto) throws SubscriptionNotFoundException;

	String setAllSubscriptionFeatures(String subscriptionCode)throws SubscriptionNotFoundException;
	
	List<String> getSubscriptionByProductCode(String productCode);
	
	SubscriptionDto getSubscriptionBySubscriptionCode(String subscriptionCode);
}