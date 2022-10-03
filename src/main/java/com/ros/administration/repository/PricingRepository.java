package com.ros.administration.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ros.administration.model.subscription.Pricing;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, UUID> {

}
