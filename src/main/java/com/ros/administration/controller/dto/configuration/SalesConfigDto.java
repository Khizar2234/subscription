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
public class SalesConfigDto {

	private List<SaleTypeDto> saleTypes;

	private List<TaxNameDto> taxInfos;

	private List<TipDto> tips;

}
