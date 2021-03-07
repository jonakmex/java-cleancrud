package com.cleancrud.test.interactor;

import com.cleancrud.test.interactor.input.CreateUnitInput;
import com.cleancrud.test.interactor.input.Input;

import java.util.Map;

public class InputFactory {
    public static Input make (String inputName, Map<String,Object> params){
        if("CreateUnitInput".equals(inputName))
            return makeCreateUnitInput(params);

        return null;
    }

    private static Input makeCreateUnitInput(Map<String, Object> params) {
        CreateUnitInput input = new CreateUnitInput();
        input.description = params.get("description").toString();
        return input;
    }

}
