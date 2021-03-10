package com.cleancrud.interactor.validator.unit;

import com.cleancrud.interactor.input.FindAllInput;
import com.skeleton.interactor.input.Input;
import com.skeleton.interactor.validator.Validator;

public class FindAllUnitsValidator implements Validator {
    @Override
    public void validate(Input input) {
        FindAllInput findAllInput = (FindAllInput) input;

    }
}
