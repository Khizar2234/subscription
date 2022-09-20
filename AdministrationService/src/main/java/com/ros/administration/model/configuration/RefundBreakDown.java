package com.ros.administration.model.configuration;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class RefundBreakDown {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "refund_id", length = 16)
	private UUID id;

	private String billNumber;

	private String reason;

	private String refundName;

	private String refundCode;

}
