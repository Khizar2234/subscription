package com.ros.administration.model.configuration;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ThirdPartySource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "third_src_party_id", length = 16)
	private UUID id;
	
	private String code;
	
	private String title;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "third_party_category_id")
	private ThirdPartyCategory thirdPartyCategory;
	

}
