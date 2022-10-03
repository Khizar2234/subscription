package com.ros.administration.mapper;



import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ros.administration.controller.dto.ProductDto;
import com.ros.administration.model.Feature;
import com.ros.administration.model.Product;
import com.ros.administration.repository.FeatureRepository;


/**
 * 
 * This interface converts user entities to dtos
 *
 */
@Mapper
//(componentModel="")
@Component
public abstract class ProductMapper {
	
	@Autowired
	protected FeatureRepository featureRepository;
	
	//ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
	
	@Mapping(target = "subscriptions", source = "subscriptions")
	public abstract ProductDto  convertToProductDto( Product product);
	
	@Mapping(target = "productName", source = "productName")
	public abstract Product  convertToProduct( ProductDto productDto);

	
	
	String toString(Feature feature) {
		return feature.getFeatureCode();
	}
	
	Feature toFeature(String featureCode) {
		Optional<Feature> feature=featureRepository.findByFeatureCode(featureCode);
		if(feature.isPresent()) {
			return feature.get();
		}else {
			return null;
		}
	}
	
	
	
	
}
