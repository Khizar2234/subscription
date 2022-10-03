package com.ros.administration.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.administration.controller.dto.ProductDto;
import com.ros.administration.controller.dto.ProductModulesAndSubCountDto;
import com.ros.administration.exceptions.ProductNotFoundException;
import com.ros.administration.mapper.ProductMapper;
import com.ros.administration.model.FunctionalModule;
import com.ros.administration.model.Product;
import com.ros.administration.repository.ProductRepository;
import com.ros.administration.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired 
	private ProductRepository productRepository;


	@Autowired 
	private ProductMapper productMapper;



	@Override
	public List<ProductDto> getAllProducts() throws ProductNotFoundException {
		List<Product> producList=productRepository.findAll();
		List<ProductDto> productDtoList=new ArrayList<>();
		if(producList!=null) {

			for(Product pr:producList) {
				ProductDto productDto = productMapper.convertToProductDto(pr);
				productDtoList.add(productDto);
			}
		}
		return productDtoList;
	}

	
	@Override
	public ProductModulesAndSubCountDto getProductModulesAndSubCount(String productCode) throws ProductNotFoundException {
		Product product=productRepository.findProductByCode( productCode);
		int count=0;
		ProductModulesAndSubCountDto countDto = new ProductModulesAndSubCountDto(); 
		countDto.setBusinessModuleCount(product.getBusinessModules().size());
		countDto.setFunctionalModuleCount(product.getBusinessModules()
				                          .stream()
				                          .mapToLong(businessModule ->businessModule.getFunctionalModules().size())
				                          .sum());	    
		countDto.setFeatureCount(product.getBusinessModules()
                .stream()
                .mapToLong(businessModule ->businessModule.getFunctionalModules()
                		.stream()
                		.mapToLong(functionalModule-> functionalModule.getFeatures().size())
                		.sum()
                		)
                .sum());	
		countDto.setSubscriptionCount(product.getSubscriptions().size());
		return countDto;
	}
}
