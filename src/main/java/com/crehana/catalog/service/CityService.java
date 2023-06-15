package com.crehana.catalog.service;

import com.crehana.catalog.constants.PersistenceConstants;
import com.crehana.catalog.dto.CityDTO;
import com.crehana.catalog.exception.CrehanaException;
import com.crehana.catalog.persistence.CityPersistence;

import java.util.List;
import java.util.Optional;

public class CityService {

    private final CityPersistence persistence;

    public CityService(CityPersistence persistence) {
        this.persistence = persistence;
    }

    public Optional<CityDTO> existCity(String code) {
        return persistence.existCity(code);
    }

    public void update(CityDTO city) {
        Optional<CityDTO> existCity = persistence.existCity(city.getCode());

        if(existCity.isPresent()) {
            persistence.update(city);
        } else {
            throw new CrehanaException(PersistenceConstants.CITY_NOT_EXIST);
        }
    }

    public void create(CityDTO city) {
        Optional<CityDTO> existCity = persistence.existCity(city.getCode());

        if(existCity.isPresent()) {
            persistence.create(city);
        } else {
            throw new CrehanaException(PersistenceConstants.CITY_NOT_EXIST);
        }
    }

    public List<CityDTO> getAll() {
       return persistence.getAll();
    }
}
