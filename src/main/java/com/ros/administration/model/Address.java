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
import javax.persistence.OneToOne;

import com.ros.administration.model.enums.EAddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id",length = 16)
	private UUID id; 
	
	@Enumerated(EnumType.STRING)
	@Column(name = "address_name")
	private EAddress addressName;

	private String city;
	
	private String state;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String zipCode;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(referencedColumnName ="country_name", name ="country_name")
	private Country country;
}
