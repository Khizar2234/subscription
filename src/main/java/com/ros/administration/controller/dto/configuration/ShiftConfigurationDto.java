package com.ros.administration.controller.dto.configuration;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShiftConfigurationDto {

    private UUID id;

    private List<RestaurantShiftsDto> restaurantShifts;
}
