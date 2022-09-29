package com.ros.administration.model.configuration;


import com.ros.administration.model.BaseEntity;
import com.ros.administration.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentConfiguration extends BaseEntity implements Serializable {

    @Id
    @Column
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<CustomDepartment> customDepartment;
}
