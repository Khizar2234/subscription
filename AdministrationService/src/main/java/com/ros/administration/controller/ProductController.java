package com.ros.administration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ros.administration.controller.dto.ProductDto;
import com.ros.administration.controller.dto.ProductModulesAndSubCountDto;
import com.ros.administration.exceptions.ProductNotFoundException;
import com.ros.administration.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin("*")
@Slf4j
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Operation(summary = "get all Products")
	@GetMapping("/getAllProducts")
	public ResponseEntity<?> getAllProducts(){
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<List<ProductDto>>(productService.getAllProducts(), HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			response = new ResponseEntity<ProductNotFoundException>(new ProductNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	@Operation(summary = "get all Products")
	@GetMapping("/ProductModulesAndSubCount")
	public ResponseEntity<?> getAllProducts(@RequestParam String productCode){
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<ProductModulesAndSubCountDto>(productService.getProductModulesAndSubCount(productCode), HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			response = new ResponseEntity<ProductNotFoundException>(new ProductNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
}
