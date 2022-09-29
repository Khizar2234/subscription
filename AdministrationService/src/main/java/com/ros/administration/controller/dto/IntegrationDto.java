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

import com.ros.administration.model.enums.IntegrationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntegrationDto {
	
	private UUID id;
	
	private String name;
	
	private boolean termsAndConditions;
	
	private String code;

	private String logo;
	
	private IntegrationType type ;
	
	private String description;

	private String integrationFormat;

}
