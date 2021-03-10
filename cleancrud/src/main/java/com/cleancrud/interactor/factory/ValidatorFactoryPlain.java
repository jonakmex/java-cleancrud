package com.cleancrud.interactor.factory;


import com.cleancrud.interactor.validator.CreateUnitValidator;
import com.skeleton.interactor.factory.ValidatorFactory;
import com.skeleton.interactor.validator.Validator;


public class ValidatorFactoryPlain implements ValidatorFactory {

    public static final CreateUnitValidator CREATE_UNIT_VALIDATOR = new CreateUnitValidator();

    @Override
    public Validator make(String validatorName) {
        if("CreateUnitValidator".equals(validatorName))
            return CREATE_UNIT_VALIDATOR;
        return null;
    }

}
