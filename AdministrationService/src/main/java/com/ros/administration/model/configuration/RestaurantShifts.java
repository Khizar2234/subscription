package com.ros.administration.model.configuration;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RestaurantShifts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID shiftId;

    private String shiftName;
    private String shiftDescription;
    private int breakDuration;

    @Enumerated(EnumType.STRING)
    private Booking booking;

    @Enumerated(EnumType.STRING)
    private PayrollHours payrollHours;

    @Enumerated(EnumType.STRING)
    private Schedule schedule;


}
