package com.ros.administration.service.impl;

import com.ros.administration.controller.dto.CountryDto;
import com.ros.administration.exceptions.CountryAlreadyExistsException;
import com.ros.administration.mapper.CountryMapper;
import com.ros.administration.model.Country;
import com.ros.administration.repository.CountryRepository;
import com.ros.administration.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;


    @Override
    public List<CountryDto> get() {
        List<Country> countries = countryRepository.get();
        return countryMapper.convertToCountryDtoList(countries);
    }

    @Override
    public CountryDto get(UUID uuid) {
        Country country = countryRepository.findByUUID(uuid);
        return countryMapper.convertToCountryDto(country);
    }

	@Override
	public List<CountryDto> addAllCountries(List<CountryDto> countriesDto) throws CountryAlreadyExistsException{
		List<Country>countries = countryMapper.convertToCountryList(countriesDto);
		countries.forEach(country -> country = countryRepository.save(country));
		return countryMapper.convertToCountryDtoList(countries);
	}
}
