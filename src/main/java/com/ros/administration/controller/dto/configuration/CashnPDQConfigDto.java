package com.ros.administration.controller.dto.configuration;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashnPDQConfigDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TillTypeDto> tillTypes;

	private List<PettyCashTypeDto> pettyCashTypes;

	private List<PDQTypeDto> pdqTypes;

}
