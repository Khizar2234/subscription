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
public class Pricing extends BaseEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pricing_id",length = 16)
	private UUID id;
	
	@Column(name = "pricing_frequency")
	private String frequency;
	
	@Column(name = "pricing_pricingType")
	private String pricingType;
	
	@Column(name = "pricing_cost")
	private double cost;

}
