package com.crehana.catalog.validator;

import com.crehana.catalog.enums.MenuOption;
import org.apache.commons.lang3.math.NumberUtils;

public class MenuOptionValidator {

    public boolean isValid(String option) {
        Boolean isValid = Boolean.TRUE;

        if (!NumberUtils.isDigits(option)) {
            isValid = Boolean.FALSE;
        } else {
            MenuOption checkOption = MenuOption.findByCode(Integer.valueOf(option));
            if(checkOption == null) {
                isValid = Boolean.FALSE;
            }
        }

        return isValid;
    }
}
