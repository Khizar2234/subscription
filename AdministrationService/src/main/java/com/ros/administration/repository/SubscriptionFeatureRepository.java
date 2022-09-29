package com.ros.administration.repository;

import java.util.List; 
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.administration.model.subscription.SubscriptionFeature;

@Repository
public interface SubscriptionFeatureRepository extends JpaRepository<SubscriptionFeature, UUID> {
	
	@Query(value = "select * from subscription_feature sf where sf.subscription_Code = :subscriptionCode and sf.feature_code = :featureCode ",nativeQuery = true)
	Optional <SubscriptionFeature> findSubscriptionFeatureForUserPermission(@Param("subscriptionCode") String subscriptionCode,@Param("featureCode") String featureCode);

	
	@Query(value = "select * from subscription_feature sf where sf.subscription_Code = :subscriptionCode",nativeQuery = true)
	List<SubscriptionFeature> findSubscriptionFeaturesBySubscriptionCode(@Param("subscriptionCode") String subscriptionCode);
}
