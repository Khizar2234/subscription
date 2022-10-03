package com.ros.administration.controller.dto.subscription;

//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ros.administration.controller.SubscriptionController;
import com.ros.administration.mapper.SubscriptionMapper;
import com.ros.administration.repository.SubscriptionRepository;
import com.ros.administration.service.impl.SubscriptionServiceImpl;

// @RunWith(MockitoJUnitRunner.class)
// class SubscriptionDtoTest {

// 	SubscriptionDto subscriptionDto;
// 	List<PricingDto> pricings;
// 	List<SubscriptionFeatureDto> subscriptionFeatures;
// 	SubscriptionPackageSpecificationDto subscriptionPackageSpecification;
// 	private MockMvc mockMvc;
	
// 	ObjectMapper Objectmapper = new ObjectMapper();
// 	ObjectWriter objectWriter = Objectmapper.writer();
	
// 	@Mock
//     private SubscriptionRepository subscription_repository;
	
// 	@Mock
// 	private SubscriptionServiceImpl subscription_service_Impl = new SubscriptionServiceImpl();
// //	
// //	@Mock
// //    private SubscriptionMapper subscriptionMapper;
    
//     @InjectMocks
//     private SubscriptionController subscription_controller;
    
// 	@Before
// 	public void setup() {
// 		subscriptionDto = mock(SubscriptionDto.class);
// 		PricingDto pricingDto1 = mock(PricingDto.class);
// 		PricingDto pricingDto2 = mock(PricingDto.class);
// 		SubscriptionFeatureDto subscriptionDtoFeature1 = mock(SubscriptionFeatureDto.class);
// 		SubscriptionFeatureDto subscriptionDtoFeature2 = mock(SubscriptionFeatureDto.class);
// 		subscriptionPackageSpecification = mock(SubscriptionPackageSpecificationDto.class);
// 		// subscriptionDto
// 		UUID idsubscriptionDto = null;
// 		String name = "Premium";
// 		String productCode = "P101";
// 		String subscriptionDuration = "180";
// 		boolean subscriptionActive = true;
// 		String description = "Premium Sbuscription with 180 day access.";
// 		String subscriptionCode = "P101";
// 		// PricingDto 1
// 		UUID idPricingDto1 = null;
// 		String frequency1 = "Monthly";
// 		String pricingType1 = "USER";
// 		double cost1 = 1290;
// 		// PricingDto 2
// 		UUID idPricingDto2 = null;
// 		String frequency2 = "Yearly";
// 		String pricingType2 = "CHAIN";
// 		double cost2 = 11999;
// 		// SubscriptionFeatureDto f1
// 		UUID idSubscriptionFeatureDtoF1 = null;
// 		String feature1 = "Feature1";
// 		boolean subscriptionFeatureActive1 = true;
// 		// SubscriptionFeatureDto f2
// 		UUID idSubscriptionFeatureDtoF2 = null;
// 		String feature2 = "Feature2";
// 		boolean subscriptionFeatureActive2 = false;
// 		//subscriptionPackageSpecification
// 		UUID idSubscriptionPackageSpecification = null;
// 		int noOfCashUpSheet =100;
// 		int noOfRestaurant=25;
// 		int noOfEmployee=150;
// 		int userCount=300;
// 		String storageLimit= "2TB";
// 		//subscriptionDto
// 		subscriptionDto.setDescription(description);
// 		subscriptionDto.setId(idsubscriptionDto);
// 		subscriptionDto.setName(name);
// 		subscriptionDto.setProductCode(productCode);
// 		subscriptionDto.setSubscriptionActive(subscriptionActive);
// 		subscriptionDto.setSubscriptionCode(subscriptionCode);
// 		subscriptionDto.setSubscriptionDuration(subscriptionDuration);
// 		//sub1 features
// 		subscriptionDtoFeature1.setId(idSubscriptionFeatureDtoF1);
// 		subscriptionDtoFeature1.setFeature(feature1);
// 		subscriptionDtoFeature1.setSubscriptionFeatureActive(subscriptionFeatureActive1);
// 		//sub2 features
// 		subscriptionDtoFeature2.setId(idSubscriptionFeatureDtoF2);
// 		subscriptionDtoFeature2.setFeature(feature2);
// 		subscriptionDtoFeature2.setSubscriptionFeatureActive(subscriptionFeatureActive2);
// 		//adding sub1 and sub2 in list
// 		subscriptionFeatures.add(subscriptionDtoFeature1);
// 		subscriptionFeatures.add(subscriptionDtoFeature2);
// 		//subscriptionPackageSpecification
// 		subscriptionPackageSpecification.setId(idSubscriptionPackageSpecification);
// 		subscriptionPackageSpecification.setNoOfCashUpSheet(noOfCashUpSheet);
// 		subscriptionPackageSpecification.setNoOfEmployee(noOfEmployee);
// 		subscriptionPackageSpecification.setNoOfRestaurant(noOfRestaurant);
// 		subscriptionPackageSpecification.setStorageLimit(storageLimit);
// 		subscriptionPackageSpecification.setUserCount(userCount);
// 		//pricingDto--1
// 		pricingDto1.setId(idPricingDto1);
// 		pricingDto1.setFrequency(frequency1);
// 		pricingDto1.setPricingType(pricingType1);
// 		pricingDto1.setCost(cost1);
// 		//pricingDto--2
// 		pricingDto2.setId(idPricingDto2);
// 		pricingDto2.setFrequency(frequency2);
// 		pricingDto2.setPricingType(pricingType2);
// 		pricingDto2.setCost(cost2);
// 		//adding pDto1 and pDto2 to PDto-List
// 		pricings.add(pricingDto1);
// 		pricings.add(pricingDto2);
// 		//final set-up
// 		when(subscriptionDto.getId()).thenReturn(idsubscriptionDto);
// 		when(subscriptionDto.getName()).thenReturn(name);
// 		when(subscriptionDto.getProductCode()).thenReturn(productCode);
// 		when(subscriptionDto.getSubscriptionDuration()).thenReturn(subscriptionDuration);
// 		when(subscriptionDto.isSubscriptionActive()).thenReturn(subscriptionActive);
// 		when(subscriptionDto.getSubscriptionFeatures()).thenReturn(subscriptionFeatures);
// 		when(subscriptionDto.getSubscriptionPackageSpecification()).thenReturn(subscriptionPackageSpecification);
// 		when(subscriptionDto.getDescription()).thenReturn(description);
// 		when(subscriptionDto.getSubscriptionCode()).thenReturn(subscriptionCode);
// 		when(subscriptionDto.getPricings()).thenReturn(pricings);
// 		MockitoAnnotations.initMocks(this);
// 		this.mockMvc = MockMvcBuilders.standaloneSetup(subscription_controller).build();
// 	}

	
// 	@Test
// 	void test() throws Exception {
// 	    SubscriptionDto MainDto = subscriptionDto;
// 	    Mockito.when(subscription_service_Impl.addSubscription(MainDto)).thenReturn(MainDto);
	    
// 	    mockMvc.perform(MockMvcRequestBuilders
// 	            .post("/add",MainDto)
// 	            .contentType(MediaType.APPLICATION_JSON))
// 	            .andExpect(status().isOk());
// //	            .andExpect(MockMvcResultMatchers.jsonPath("$",MainDto));
// 	}

// }
