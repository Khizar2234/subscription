package com.ros.administration.mapper;

import com.ros.administration.controller.dto.configuration.ShiftConfigurationDto;
import com.ros.administration.model.configuration.RestaurantShifts;
import com.ros.administration.model.configuration.ShiftConfiguration;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
@Component
public interface ShiftConfigurationMapper {

    ShiftConfigurationMapper instance = Mappers.getMapper(ShiftConfigurationMapper.class);

    ShiftConfigurationDto convertToShiftConfigurationDto(ShiftConfiguration shiftConfiguration);
    ShiftConfiguration convertToShiftConfigurationEntity(ShiftConfigurationDto shiftConfigurationDto);

//    void updateShiftConfiguration(ShiftConfigurationDto shiftConfigurationDto, @MappingTarget ShiftConfiguration shiftConfiguration);
}
