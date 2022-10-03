package com.ros.administration.controller.dto.configuration;

import com.ros.administration.model.configuration.Booking;
import com.ros.administration.model.configuration.PayrollHours;
import com.ros.administration.model.configuration.Schedule;
import lombok.Data;

import java.util.UUID;

@Data
public class RestaurantShiftsDto {
    private UUID shiftId;

    private String shiftName;

    private String shiftDescription;

    private int breakDuration;

    private Booking booking;

    private PayrollHours payrollHours;

    private Schedule schedule;
}
