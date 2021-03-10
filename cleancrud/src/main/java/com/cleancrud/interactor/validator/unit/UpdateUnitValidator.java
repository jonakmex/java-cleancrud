package com.cleancrud.interactor.validator.unit;

import com.cleancrud.interactor.input.UpdateUnitInput;
import com.skeleton.interactor.exception.Code;
import com.skeleton.interactor.exception.InputException;
import com.skeleton.interactor.input.Input;
import com.skeleton.interactor.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class UpdateUnitValidator implements Validator {
    @Override
    public void validate(Input input) {
        UpdateUnitInput updateUnitInput = (UpdateUnitInput) input;
        Map<String,Map<String,String>> errors = new HashMap<>();
        Map<String, String> values = UnitValidation.description(updateUnitInput.description);
        if(!values.isEmpty())
            errors.put("description",values);


        if(!errors.isEmpty())
            throw new InputException(Code.ERR_INVALID_INPUT,errors);
    }
}
