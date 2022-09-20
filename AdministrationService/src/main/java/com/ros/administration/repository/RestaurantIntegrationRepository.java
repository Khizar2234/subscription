package com.ros.administration.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ros.administration.model.Integration;
import com.ros.administration.model.RestaurantIntegration;

@Repository
public interface RestaurantIntegrationRepository extends JpaRepository<RestaurantIntegration, UUID> {
	
    @Transactional
	@Query(value="SELECT EXISTS(SELECT * FROM restaurant_integration WHERE integration_id = :integrationId and restaurant_id =:restaurantId)",nativeQuery = true)
	boolean checkifIntegrationExists(@Param("integrationId")UUID integrationId,@Param("restaurantId") UUID restaurantId);

    @Transactional
    @Modifying
    @Query(value="delete from restaurant_integration where restaurant_integration_id=:restaurantIntegrationId",nativeQuery = true)
	 void deleteRestaurantIntegration(@Param("restaurantIntegrationId") UUID restaurantIntegrationId);


}
