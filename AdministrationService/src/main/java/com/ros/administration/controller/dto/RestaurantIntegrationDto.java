package com.ros.administration.controller.dto;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ros.administration.model.Integration;
import com.ros.administration.model.enums.IntegrationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantIntegrationDto {
	
	private UUID id;
	
	private boolean integrationStatus;
	
	private IntegrationDto integration;
	
	private String integrationCredentials ;

}
