package com.crehana.catalog.controller;

import com.crehana.catalog.constants.ControllerConstants;
import com.crehana.catalog.enums.MenuOption;
import com.crehana.catalog.validator.MenuOptionValidator;

import java.util.Scanner;

public class MenuController {

    private final MenuOptionValidator validator;
    private final CityController cityController;
    private final Scanner scanner;

    public MenuController(MenuOptionValidator validator,
                          CityController cityController,
                          Scanner scanner) {
        this.validator = validator;
        this.cityController = cityController;
        this.scanner = scanner;
    }

    public void draw() {
        MenuOption option = null;

        createMenu();

        do {
            String inputScanner = scanner.next();
            if(!validator.isValid(inputScanner)) {
                System.out.println(ControllerConstants.MENU_INVALID_OPTION);
                System.out.print(ControllerConstants.MENU_CHOOSE_OPTION);
            } else {
                option = MenuOption.findByCode(Integer.valueOf(inputScanner));
                redirectAction(option);

                if(MenuOption.EXIT != option) {
                    createMenu();
                }
            }

        } while (MenuOption.EXIT != option);
    }

    private void createMenu() {
        System.out.println(ControllerConstants.MENU_TITLE);

        for (MenuOption menuOption : MenuOption.values()) {
            System.out.println(formatOption(menuOption));
        }

        System.out.print(ControllerConstants.MENU_CHOOSE_OPTION);
    }

    private String formatOption(MenuOption option) {
        return String.format("%s - %s", option.getCode(), option.getText());
    }

    private void redirectAction(MenuOption option) {
        switch (option) {
            case CREATE:
                cityController.create();
                break;
            case UPDATE:
                cityController.update();
                break;
            case LIST:
                cityController.getAll();
                break;
        }
    }
}
