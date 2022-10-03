package com.ros.administration.model.subscription;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ros.administration.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionPackageSpecification extends BaseEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subscriptionPackageSpecification_id",length = 16)
	private UUID id;
	
	@Column(name = "subscriptionPackageSpecification_noOfCashUpSheet")
	private Integer noOfCashUpSheet;
	
	@Column(name = "subscriptionPackageSpecification_noOfRestaurant")
	private Integer noOfRestaurant;
	
	@Column(name = "subscriptionPackageSpecification_noOfEmployee")
	private Integer noOfEmployee;
	
	@Column(name = "subscriptionPackageSpecification_userCount")
	private Integer userCount;
	
	@Column(name = "subscriptionPackageSpecification_storageLimit")
	private String storageLimit;

}
