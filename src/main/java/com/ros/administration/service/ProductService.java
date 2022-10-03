package com.ros.administration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ros.administration.controller.dto.ProductDto;
import com.ros.administration.controller.dto.ProductModulesAndSubCountDto;
import com.ros.administration.exceptions.ProductNotFoundException;

@Service
public interface ProductService {

	List<ProductDto> getAllProducts() throws ProductNotFoundException;

	ProductModulesAndSubCountDto getProductModulesAndSubCount(String productCode) throws ProductNotFoundException;
}
