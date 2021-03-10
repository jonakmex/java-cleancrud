package com.cleancrud.interactor.validator.unit;

import java.util.HashMap;
import java.util.Map;

public class UnitValidation {
    private static final int MIN_LEN_DESCRIPTION = 3;
    private static final int MAX_LEN_DESCRIPTION = 50;

    public static Map<String,String> description(String description){

        Map<String,String> values = new HashMap<>();

        if(description.length() < MIN_LEN_DESCRIPTION)
            values.put("length",description.length()+"");

        if(description.length() > MAX_LEN_DESCRIPTION)
            values.put("length",description.length()+"");

        return values;
    }
}
