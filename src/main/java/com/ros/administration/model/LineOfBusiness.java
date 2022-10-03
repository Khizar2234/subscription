package com.ros.administration.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ros.administration.model.account.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//
//@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class LineOfBusiness extends BaseEntity implements Serializable {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "line_of_business_id",length = 16)
//	private UUID id;
//	
//	@Column(name = "lob_name")
//	private String LOBName;
//
//	@Column(name = "lob_code")
//	private String LOBCode;
//	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "lob_id")
//	private List<BusinessModule> businessModules;
}
