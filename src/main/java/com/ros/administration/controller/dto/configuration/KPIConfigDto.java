package com.ros.administration.controller.dto.configuration;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KPIConfigDto {

	private List<KPITypeDto> kpiTypes;

	private ComplaintDto complaints;

	private RefundBreakDownDto refundBreakDowns;

	private DiscountBreakDownDto breakDownDetails;

}
