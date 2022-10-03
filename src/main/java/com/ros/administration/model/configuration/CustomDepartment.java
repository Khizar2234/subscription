package com.ros.administration.model.configuration;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CustomDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID customId;

    private String departmentName;

    private String departmentDescription;
}
