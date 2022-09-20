package com.ros.administration.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.administration.model.Product;

@Repository

public interface ProductRepository extends JpaRepository<Product, UUID>  {
	
	@Query("select p from Product p where p.productCode=:productCode")
	Product findProductByCode(@Param("productCode") String productCode);

}
