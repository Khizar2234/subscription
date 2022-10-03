package com.ros.administration.mapper;

import com.ros.administration.controller.dto.configuration.DepartmentConfigurationDto;
import com.ros.administration.model.configuration.DepartmentConfiguration;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DepartmentConfigurationMapper {

//    DepartmentConfiguration convertToDepartmentConfigurationEntity(DepartmentConfigurationDto departmentConfigurationDto);
//    DepartmentConfigurationDto convertToDepartmentConfigurationDto(DepartmentConfiguration departmentConfiguration);

    DepartmentConfiguration convertToDepartmentConfigurationEntity(DepartmentConfigurationDto departmentConfigurationDto);
    DepartmentConfigurationDto convertToDepartmentConfigurationDto(DepartmentConfiguration departmentConfiguration);
}
