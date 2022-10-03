package com.ros.administration.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.administration.model.Feature;
import com.ros.administration.model.subscription.Subscription;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, UUID> {

	@Query("select f from Feature f where f.featureCode = :featureCode")
	Optional<Feature> findByFeatureCode(@Param("featureCode") String featureCode);

	@Query("select distinct f.featureCode from Feature f")
	List<String> findFeatureCodes();
	
	@Query("select f from Feature f where f.featureCode is not null")
	List<Feature> findFeatures();

}
