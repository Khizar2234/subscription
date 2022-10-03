package com.ros.administration.controller.dto.configuration;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ros.administration.model.configuration.EInterval;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashUpIntervalDto {

	private EInterval eInterval;

//	@Temporal(value = TemporalType.TIME)
	private Date startTime;

//	@Temporal(value = TemporalType.TIME)
	private Date endTime;
}
