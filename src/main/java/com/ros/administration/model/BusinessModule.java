package com.ros.administration.model;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BusinessModule extends BaseEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "business_module_id",length = 16)
	private UUID id;
	
	@Column(name = "business_module_name")
	private String name;
	
	@Column(name = "business_module_code")
	private String code;
	
	@Column
	private String displayName;
	
	private boolean active;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "lob_id")
//	private LineOfBusiness lineOfBusiness;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="business_module_code",referencedColumnName = "business_module_code")
	private List<FunctionalModule> functionalModules;

	
}
