package com.ros.administration.controller.dto.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartySourceDto {

	private String title;

	private ThirdPartyCategoryDto thirdPartyCategory;

}
