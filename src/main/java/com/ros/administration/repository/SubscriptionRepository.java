package com.ros.administration.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.administration.model.subscription.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
	@Query("select s from Subscription s where s.subscriptionCode = :subscriptionCode")
	Optional<Subscription> findBySubscriptionCode(@Param("subscriptionCode") String subscriptionCode);
	
	@Query("select s from Subscription s")
	List<Subscription> getAllSubScription();
	
	@Query("select s from Subscription s where s.subscriptionCode is not null")
	List<Subscription> findSubscriptions();

}
