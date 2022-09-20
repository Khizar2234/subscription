package com.ros.administration.controller.dto.configuration;

import com.ros.administration.controller.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentConfigurationDto {

    private UUID id;

    private List<CustomDepartmentDto> customDepartment;
}
