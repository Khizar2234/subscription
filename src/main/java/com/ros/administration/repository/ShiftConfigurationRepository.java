package com.ros.administration.repository;

import com.ros.administration.controller.dto.configuration.ShiftConfigurationDto;
import com.ros.administration.model.configuration.ShiftConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShiftConfigurationRepository extends JpaRepository<ShiftConfiguration, UUID> {

    @Query(value = "select s from ShiftConfiguration s where s.id = :id")
    public ShiftConfiguration findByRestaurantId(UUID id);
}
