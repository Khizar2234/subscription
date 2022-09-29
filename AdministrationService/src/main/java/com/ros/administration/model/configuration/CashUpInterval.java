package com.ros.administration.model.configuration;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CashUpInterval {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cashup_interval_id", length = 16)
	private UUID id;

	@Enumerated(EnumType.STRING)
	private EInterval eInterval;

//	@Temporal(value = TemporalType.TIME)
	private Date startTime;

//	@Temporal(value = TemporalType.TIME)
	private Date endTime;

}
