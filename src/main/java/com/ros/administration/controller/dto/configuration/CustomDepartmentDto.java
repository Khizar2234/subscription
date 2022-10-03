package com.ros.administration.controller.dto.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomDepartmentDto {

    private UUID customId;

    private String departmentName;

    private String departmentDescription;
}
