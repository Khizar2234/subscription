package com.ros.administration.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ros.administration.controller.dto.subscription.SubscriptionDto;
import com.ros.administration.controller.dto.subscription.SubscriptionFeatureDto;
import com.ros.administration.model.Feature;
import com.ros.administration.model.subscription.Subscription;
import com.ros.administration.model.subscription.SubscriptionFeature;
import com.ros.administration.repository.FeatureRepository;
import com.ros.administration.repository.SubscriptionRepository;

@Mapper
//(componentModel="spring")
@Component
public abstract class SubscriptionMapper {
	
	@Autowired
	protected FeatureRepository featureRepository;
	
	@Autowired
	protected SubscriptionRepository subscriptionRepository;
	
	//SubscriptionMapper mapper = Mappers.getMapper(SubscriptionMapper.class);
	public abstract SubscriptionDto convertToSubscriptionDto(Subscription subscription);

	public abstract Subscription convertToSubscription(SubscriptionDto subscriptionDto);
	
	public abstract SubscriptionFeatureDto convertToSubscriptionDto(SubscriptionFeature subscriptionFeature);

	public abstract SubscriptionFeature convertToSubscription(SubscriptionFeatureDto subscriptionFeatureDto);
	
	
	
	
	String toString(Feature feature) {
		if (feature!=null && feature.getFeatureCode()!=null) {
		return feature.getFeatureCode();
		}
		else return "";
	}
	
	Feature toFeature(String featureCode) {
		Optional<Feature> feature=featureRepository.findByFeatureCode(featureCode);
		if(feature.isPresent()) {
			return feature.get();
		}else {
			return null;
		}
	}
	
	Subscription toSubscription(String subscriptioncode) {
        Optional<Subscription> subscription = subscriptionRepository.findBySubscriptionCode(subscriptioncode);
        if (subscription.isPresent()) {
            return subscription.get();
        } else {
            return null;
        }
    }
	
	String toString(Subscription subscription) {
        return subscription.getSubscriptionCode();
    }

	public abstract void updateSubscription(SubscriptionDto subscriptionDto,@MappingTarget Subscription subscription);

	public abstract void updateSubscriptionFeatures(List<SubscriptionFeatureDto> subscriptionFeatureDtos,
    		@MappingTarget List<SubscriptionFeature> subscriptionFeatures);
	
}