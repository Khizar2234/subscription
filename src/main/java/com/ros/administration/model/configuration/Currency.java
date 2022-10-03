package com.ros.administration.model.configuration;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Currency {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "currency_id", length = 16)
	private UUID id;
	
	@Enumerated(EnumType.STRING)
	private CurrencyType currencyType;
	
	@Enumerated(EnumType.STRING)
	private CurrencySymbol symbol;
}
