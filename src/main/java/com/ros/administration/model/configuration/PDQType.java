package com.ros.administration.model.configuration;

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
import javax.persistence.OneToMany;
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
public class PDQType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pdq_type_id", length = 16)
	private UUID id;

	private String code;

	private String title;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pdq_type_id")
	private List<PDQCard> pdqCard;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pdq_machine_id")
	private PDQMachine pdqMachine;

}
