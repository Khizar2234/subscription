package com.ros.administration.repository;

import com.ros.administration.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {

    @Query("select u from Country u ")
    List<Country> get();

    @Query("select u from Country u where u.id = :id")
    Country findByUUID(@Param("id") UUID id);
}
