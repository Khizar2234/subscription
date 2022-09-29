package com.ros.administration.model;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ros.administration.model.enums.Gender;
import com.ros.administration.model.enums.IntegrationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantIntegration extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "restaurant_integration_id",length = 16)
	private UUID id;
	
	private boolean integrationStatus;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="integration_id")
	private Integration integration;
//	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="integration_credential_id")
	private String integrationCredentials;

}
