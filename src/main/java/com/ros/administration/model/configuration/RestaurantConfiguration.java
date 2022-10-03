package com.ros.administration.model.configuration;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ros.administration.model.BaseEntity;
import com.ros.administration.model.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="restaurant_config")
public class RestaurantConfiguration extends BaseEntity implements Serializable {

	/**
	 * Sriharsha
	 * */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="restaurant_config_id")
	private UUID id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="restaurant_config_id")
	private List<CashUpInterval> cashUpIntervals;

	@OneToOne(cascade = { CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "currency_id")
	private Currency currency;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cash_n_pdq_id")
	private CashnPDQConfig cashnPdqConfig;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "third_party_id")
	private ThirdPartyConfig thirdPartyConfig;





}
