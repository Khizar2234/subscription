package com.ros.administration.controller.dto;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ros.administration.model.BusinessModule;
import com.ros.administration.model.FunctionalModule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureDto {

	private UUID id;

	private String featureName;

	private String featureCode;

	private String displayName;

	private boolean active;

}
