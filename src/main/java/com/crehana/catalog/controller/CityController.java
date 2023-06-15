package com.crehana.catalog.controller;

import com.crehana.catalog.constants.ControllerConstants;
import com.crehana.catalog.dto.CityDTO;
import com.crehana.catalog.exception.CrehanaException;
import com.crehana.catalog.service.CityService;
import com.crehana.catalog.validator.CityValidator;

import java.util.List;
import java.util.Scanner;

public class CityController {
    private final CityValidator cityValidator;
    private final Scanner scanner;
    private final CityService cityService;

    public CityController(CityValidator cityValidator, Scanner scanner, CityService cityService) {
        this.cityValidator = cityValidator;
        this.scanner = scanner;
        this.cityService = cityService;
    }

    public void create() {
        System.out.println(ControllerConstants.CREATE_TITLE);

        try {
            CityDTO city = obtainCityInformation();
            cityService.create(city);
            System.out.println(ControllerConstants.CREATED_CITY_SUCCESS);
        } catch(CrehanaException e) {
            System.err.println(String.format(ControllerConstants.ERROR_MSG, e.getMessage()));
        }
    }

    public void update() {
        System.out.println(ControllerConstants.UPDATE_TITLE);

        try {
            CityDTO city = obtainCityInformation();
            cityService.update(city);
            System.out.println(ControllerConstants.UPDATED_CITY_SUCCESS);
        } catch(CrehanaException e) {
            System.err.println(String.format(ControllerConstants.ERROR_MSG, e.getMessage()));
        }
    }

    public void getAll() {
        System.out.println(ControllerConstants.LIST_TITLE);

        try {
            List<CityDTO> cities = cityService.getAll();
            cities.forEach(city -> System.out.println(String.format("CODE: %s  - NAME: %s", city.getCode(), city.getName())));
        } catch(CrehanaException e) {
            System.err.println(String.format(ControllerConstants.ERROR_MSG, e.getMessage()));
        }
    }

    private CityDTO obtainCityInformation() {
        System.out.print(ControllerConstants.CODE_CITY);
        String code = scanner.next();

        System.out.print(ControllerConstants.NAME_CITY);
        String name = scanner.next();

        CityDTO city = new CityDTO(code, name);

        cityValidator.isValid(city);

        return city;
    }
}
