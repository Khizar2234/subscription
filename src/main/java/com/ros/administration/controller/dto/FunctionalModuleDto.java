package com.ros.administration.controller.dto;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.ros.administration.model.BusinessModule;
import com.ros.administration.model.Feature;
import com.ros.administration.model.FunctionalModule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FunctionalModuleDto {

	private UUID id;

	private String name;

	private String code;

	private String displayName;

	private boolean active;

	private List<FeatureDto> features;

}
