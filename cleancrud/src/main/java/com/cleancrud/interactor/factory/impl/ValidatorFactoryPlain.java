package com.cleancrud.interactor.factory.impl;

import com.cleancrud.interactor.factory.ValidatorFactory;
import com.cleancrud.interactor.validator.CreateUnitValidator;
import com.cleancrud.interactor.validator.Validator;

public class ValidatorFactoryPlain implements ValidatorFactory {

    public static final CreateUnitValidator CREATE_UNIT_VALIDATOR = new CreateUnitValidator();

    @Override
    public Validator make(String validatorName) {
        if("CreateUnitValidator".equals(validatorName))
            return CREATE_UNIT_VALIDATOR;
        return null;
    }

}
