package com.cleancrud.interactor.factory;


import com.cleancrud.interactor.validator.unit.CreateUnitValidator;
import com.cleancrud.interactor.validator.unit.FindAllValidator;
import com.skeleton.interactor.factory.ValidatorFactory;
import com.skeleton.interactor.validator.Validator;


public class ValidatorFactoryPlain implements ValidatorFactory {

    public static final CreateUnitValidator CREATE_UNIT_VALIDATOR = new CreateUnitValidator();
    public static final FindAllValidator FIND_ALL_VALIDATOR = new FindAllValidator();

    @Override
    public Validator make(String validatorName) {
        if("CreateUnitValidator".equals(validatorName))
            return CREATE_UNIT_VALIDATOR;
        else if("FindAllUnitsValidator".equals(validatorName))
            return FIND_ALL_VALIDATOR;
        return null;
    }

}
