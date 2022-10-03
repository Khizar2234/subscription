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
public class PDQTypeDto {

	private String title;

	private List<PDQCardDto> pdqCard;

	private PDQMachineDto pdqMachine;

}
