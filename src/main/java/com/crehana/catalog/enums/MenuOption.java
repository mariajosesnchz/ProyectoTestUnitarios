package com.crehana.catalog.enums;

public enum MenuOption {

    LIST(1, "List the cities"),
    CREATE(2, "Create a city"),
    UPDATE(3, "Update a city"),
    EXIT(5, "Exit");

    private Integer code;
    private String text;

    MenuOption(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static MenuOption findByCode(Integer code) {
        for (MenuOption menuOption : values()) {
            if (menuOption.getCode().equals(code)) {
                return menuOption;
            }
        }
        return null;
    }
}
