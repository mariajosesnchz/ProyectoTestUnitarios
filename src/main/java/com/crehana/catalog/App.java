package com.crehana.catalog;

import com.crehana.catalog.controller.CityController;
import com.crehana.catalog.controller.MenuController;
import com.crehana.catalog.persistence.CityPersistence;
import com.crehana.catalog.service.CityService;
import com.crehana.catalog.validator.CityValidator;
import com.crehana.catalog.validator.MenuOptionValidator;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        MenuOptionValidator validator = new MenuOptionValidator();
        Scanner scanner = new Scanner(System.in);

        MenuController controller = new MenuController(validator, getCityController(scanner), scanner);
        controller.draw();
    }

    private static CityController getCityController(Scanner scanner) {
        CityValidator cityValidator = new CityValidator();
        CityPersistence cityPersistence = new CityPersistence();
        CityService cityService = new CityService(cityPersistence);
        return new CityController(cityValidator, scanner, cityService);
    }
}
