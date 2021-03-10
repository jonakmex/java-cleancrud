package com.cleancrud.interactor.validator;

import com.cleancrud.interactor.input.CreateUnitInput;
import com.skeleton.interactor.exception.InputException;
import com.skeleton.interactor.input.Input;
import com.skeleton.interactor.validator.Validator;

import java.util.HashMap;
import java.util.Map;

import static com.skeleton.interactor.exception.Code.ERR_INVALID_INPUT;


public class CreateUnitValidator implements Validator {
    @Override
    public void validate(Input input) {
        CreateUnitInput createUnitInput = (CreateUnitInput) input;
        Map<String,Map<String,String>> errors = new HashMap<>();

        Map<String,String> values = validateDescription(createUnitInput);
        if(!values.isEmpty())
            errors.put("description",values);

        if(!errors.isEmpty())
            throw new InputException(ERR_INVALID_INPUT,errors);

    }

    private Map<String,String> validateDescription(CreateUnitInput createUnitInput){
        Map<String,String> values = new HashMap<>();
        if(createUnitInput.description == null || createUnitInput.description.length() < 3) {
            values.put("length",createUnitInput.description.length()+"");
        }
        return values;
    }
}
