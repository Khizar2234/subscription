package com.ros.administration.controller.dto.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundBreakDownDto {

	private String billNumber;

	private String reason;

	private String refundName;

	private String refundCode;

}
