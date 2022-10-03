package com.ros.administration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.administration.controller.dto.ProductDto;
import com.ros.administration.controller.dto.account.AccountSubscriptionDto;
import com.ros.administration.controller.dto.account.AccountSubscriptionDto2;
import com.ros.administration.controller.dto.subscription.SubscriptionDto;
import com.ros.administration.exceptions.SubscriptionAlreadyExistsException;
import com.ros.administration.exceptions.SubscriptionNotFoundException;
import com.ros.administration.mapper.ProductMapper;
import com.ros.administration.mapper.SubscriptionMapper;
import com.ros.administration.model.Feature;
import com.ros.administration.model.Product;
import com.ros.administration.model.account.AccountSubscription;
import com.ros.administration.model.enums.EStatus;
import com.ros.administration.model.subscription.Subscription;
import com.ros.administration.model.subscription.SubscriptionFeature;
import com.ros.administration.model.subscription.enums.SubcriptionFrequency;
import com.ros.administration.model.subscription.enums.SubscriptionName;
import com.ros.administration.model.subscription.enums.SubscriptionPricingType;
import com.ros.administration.repository.AccountSubscriptionRepository;
import com.ros.administration.repository.FeatureRepository;
import com.ros.administration.repository.PricingRepository;
import com.ros.administration.repository.ProductRepository;
import com.ros.administration.repository.SubscriptionFeatureRepository;
import com.ros.administration.repository.SubscriptionPackageSpecificationRepository;
import com.ros.administration.repository.SubscriptionRepository;
import com.ros.administration.service.SubscriptionService;
import com.ros.administration.util.Properties;


@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private AccountSubscriptionRepository accountSubscriptionRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SubscriptionFeatureRepository subscriptionFeatureRepository;

	@Autowired
	private FeatureRepository FeatureRepository;

	@Autowired
	private SubscriptionPackageSpecificationRepository packageSpecificationRepository;

	@Autowired
	private PricingRepository pricingRepository;

	@Autowired
	private SubscriptionMapper subscriptionMapper;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public SubscriptionDto addSubscription(SubscriptionDto subsciptionDto) throws SubscriptionAlreadyExistsException {

		Subscription subscription = subscriptionMapper.convertToSubscription(subsciptionDto);
		if(getCodeIfSubscriptionExists(subsciptionDto.getSubscriptionCode())==null) {
			subscription = subscriptionRepository.save(subscription);
			SubscriptionDto subscriptionDto= subscriptionMapper.convertToSubscriptionDto(subscription);
			return subscriptionDto;
		}
		else {
			throw new SubscriptionAlreadyExistsException(Properties.subscriptionAlreadyExists+subsciptionDto.getSubscriptionCode());
		}
	}

	private String getCodeIfSubscriptionExists(String subscriptionCode) {
		Optional<Subscription> subscription =subscriptionRepository.findBySubscriptionCode(subscriptionCode);
		if(subscription.isPresent()) {
			return subscription.get().getSubscriptionCode();
		}
		return null;
	}

	@Override
	public List<String> getSubscriptionFrequency() {
		SubcriptionFrequency frequency = SubcriptionFrequency.getInstance();
		return frequency.getSubscriptionFrequency();
	}

	@Override
	public List<String> getSubscriptionName() {
		SubscriptionName subscriptionName = SubscriptionName.getInstance();
		return subscriptionName.getSubscriptionName();
	}

	@Override
	public List<String> getSubscriptionPricingType() {
		SubscriptionPricingType subscriptionPricingType = SubscriptionPricingType.getInstance();
		return subscriptionPricingType.getSubscriptionPricingType();
	}

	@Override
	public List<SubscriptionDto> getAllSubscriptions() {
		List<Subscription> subscriptions = subscriptionRepository.findSubscriptions();
		List<SubscriptionDto> subscriptionDtoList = new ArrayList<>();
		if (subscriptions != null && !subscriptions.isEmpty()) {
			for (Subscription subscription : subscriptions) {
				SubscriptionDto subscriptionDto = subscriptionMapper.convertToSubscriptionDto(subscription);
				subscriptionDtoList.add(subscriptionDto);
			}
		}
		return subscriptionDtoList;
	}

	@Override
	public SubscriptionDto getSubscriptionById(UUID id) {
		Subscription subscription = subscriptionRepository.getById(id);
		SubscriptionDto subscriptionDto = subscriptionMapper.convertToSubscriptionDto(subscription);
		return subscriptionDto;
	}

	@Override
	public AccountSubscriptionDto getAccountSubscriptionById(UUID id) {
		AccountSubscription subscription = accountSubscriptionRepository.getById(id);
		AccountSubscriptionDto subscriptionDto = new AccountSubscriptionDto();
		subscriptionDto.setAccountSubId(subscription.getId());
		//		subscriptionDto.setSubscription(subscription.getSubscription());
		subscriptionDto.setActivatedBy(subscription.getActivatedBy());
		subscriptionDto.setActivatedDate(subscription.getActivatedDate());
		subscriptionDto.setExpiryDate(subscription.getExpiryDate());
		subscriptionDto.setStatus(subscription.getStatus());
		return subscriptionDto;
	}

	@Override
	public String updateActiveOrDeactiveSubscription(SubscriptionDto subsciptionDto) {
		Subscription subscription = subscriptionRepository.getById(subsciptionDto.getId());
		subscription.setSubscriptionActive(subsciptionDto.isSubscriptionActive());
		subscription=subscriptionRepository.save(subscription);
		String statusMsg;
		if(subscription.isSubscriptionActive()){
			statusMsg = "Activition of subscription Successfully.";
		} else {
			statusMsg = "Deactivition of subscription Successfully.";
		}
		return statusMsg;
	}

	@Override
	public String updateActiveOrDeactiveAccountSubscription(AccountSubscriptionDto2 subsciptionDto) {
		AccountSubscription subscription = accountSubscriptionRepository.getById(subsciptionDto.getAccountSubId());
		if (subsciptionDto.isSubscriptionActive()) {
			subscription.setStatus(EStatus.ACTIVE);
		} else {
			subscription.setStatus(EStatus.INACTIVE);
		}
		subscription = accountSubscriptionRepository.save(subscription);
		String statusMsg;
		if (subscription.getStatus() == EStatus.ACTIVE) {
			statusMsg = "Activition of subscription Successfully.";
		} else {
			statusMsg = "Deactivition of subscription Successfully.";
		}
		return statusMsg;
	}

	@Override
	public SubscriptionDto editSubscription(SubscriptionDto subsciptionDto) throws SubscriptionNotFoundException {
		Subscription subscription = subscriptionRepository.getById(subsciptionDto.getId());
		if(subscription!=null) {
			subscription = subscriptionMapper.convertToSubscription(subsciptionDto);
			subscription = subscriptionRepository.save(subscription);
		}
		else {
			throw new SubscriptionNotFoundException(Properties.subscriptionNotFound);
		}
		return subscriptionMapper.convertToSubscriptionDto(subscription);
	}

	@Override
	public SubscriptionDto configureSubscription(SubscriptionDto subscriptionDto) throws SubscriptionNotFoundException{
		Subscription subscription = subscriptionRepository.getById(subscriptionDto.getId());
		if(subscription!=null) {
			subscriptionMapper.updateSubscriptionFeatures(subscriptionDto.getSubscriptionFeatures(),subscription.getSubscriptionFeatures());
			subscription=subscriptionRepository.save(subscription);
		}
		else {
			throw new SubscriptionNotFoundException(Properties.subscriptionNotFound);
		}
		return subscriptionMapper.convertToSubscriptionDto(subscription);

	}


	@Override
	public ProductDto addProduct(ProductDto productDto) {
		Product product = productMapper.convertToProduct(productDto);
		product = productRepository.save(product);
		return productMapper.convertToProductDto(product);
	}

	@Override
	public List<String> getAllProductName() {
		List<Product> products = productRepository.findAll();
		List<String> productNames = new ArrayList<>();
		if(products!=null && !products.isEmpty()) {
			for(Product product : products) {
				productNames.add(product.getProductName());
			}
		}
		return productNames;
	}

	private String generateSubscriptionCode() {
		Random random = new Random();
		int val = random.nextInt();
		String hex = new String();
		hex = Integer.toHexString(val);
		return hex;
	}

	@Override
	public String setAllSubscriptionFeatures(String subscriptionCode) throws SubscriptionNotFoundException {
		List<Feature> features = FeatureRepository.findFeatures();
		Optional<Subscription> optSubscription = subscriptionRepository.findBySubscriptionCode(subscriptionCode);
		if(optSubscription.isPresent()){
			Subscription subscription = optSubscription.get();

			for (Feature feature : features) {
				Optional<SubscriptionFeature> optSubFeature = subscriptionFeatureRepository.findSubscriptionFeatureForUserPermission(subscriptionCode,feature.getFeatureCode());
				if(optSubFeature.isEmpty()) {
					SubscriptionFeature subFeature = new SubscriptionFeature(UUID.randomUUID(), subscription, feature, true);
					subscription.getSubscriptionFeatures().add(subFeature);

				}
			}
			subscription =subscriptionRepository.save(subscription);
			return Properties.allSubscriptionsSaved ;
		}
		else {
			throw new SubscriptionNotFoundException(Properties.subscriptionNotFound);
		}

	}

}
