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
public class Integration extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "integration_id",length = 16)
	private UUID id;
	
	private boolean termsAndConditions;
	
	@Column(name = "integration_name")
	private String name;
	
	@Column(name = "integration_code")
	private String code;

	@Column(name = "integration_logo")
	private URI logo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "integration_type")
	private IntegrationType type ;
	
	@Column(name = "integration_description",length = 1400)
	private String description;
	
	private String integrationFormat;

}
