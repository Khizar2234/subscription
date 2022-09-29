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
public class KPIConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kpi_id", length = 16)
	private UUID id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "kpi_id")
	private List<KPIType> kpiTypes;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "complaint_id")
	private Complaint complaints;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "refund_id")
	private RefundBreakDown refundBreakDowns;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "break_down_id")
	private DiscountBreakDown breakDownDetails;

}
