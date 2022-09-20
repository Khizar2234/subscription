package com.ros.administration.service;

import com.ros.administration.controller.dto.CountryDto;
import com.ros.administration.exceptions.CountryAlreadyExistsException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CountryService {

    List<CountryDto> get() ;

    public CountryDto get(UUID uuid);

	List<CountryDto> addAllCountries(List<CountryDto> countries) throws CountryAlreadyExistsException;
}
