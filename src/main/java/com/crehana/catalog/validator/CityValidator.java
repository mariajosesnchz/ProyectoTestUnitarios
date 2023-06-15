package com.crehana.catalog.validator;

import com.crehana.catalog.dto.CityDTO;
import org.apache.commons.lang3.StringUtils;

public class CityValidator {

    private static final int CODE_LENGTH = 3;

    public boolean isValid(CityDTO city) {
        Boolean isValid = Boolean.TRUE;

        if(StringUtils.isBlank(city.getCode()) ||
                StringUtils.isBlank(city.getName()) ||
                city.getCode().length() == CODE_LENGTH) {
            isValid = Boolean.FALSE;
        }

        return isValid;
    }
}
