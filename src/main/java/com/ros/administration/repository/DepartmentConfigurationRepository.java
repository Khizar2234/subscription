package com.ros.administration.repository;

import com.ros.administration.model.configuration.DepartmentConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentConfigurationRepository extends JpaRepository<DepartmentConfiguration, UUID> {
}
