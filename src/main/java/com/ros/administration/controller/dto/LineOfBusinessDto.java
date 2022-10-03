package com.ros.administration.controller.dto;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ros.administration.controller.dto.account.ClientDto;
import com.ros.administration.model.BusinessModule;
import com.ros.administration.model.LineOfBusiness;
import com.ros.administration.model.Restaurant;
import com.ros.administration.model.account.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LineOfBusinessDto {
	private UUID id;
	
	private String LOBName;

	private String LOBCode;
	
	private List<BusinessModuleDto> businessModules;

}
