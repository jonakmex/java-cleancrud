package com.cleancrud.interactor.factory;


import com.cleancrud.interactor.validator.unit.CreateUnitValidator;
import com.cleancrud.interactor.validator.unit.FindAllUnitsValidator;
import com.cleancrud.interactor.validator.unit.UpdateUnitValidator;
import com.skeleton.interactor.factory.ValidatorFactory;
import com.skeleton.interactor.validator.Validator;


public class ValidatorFactoryPlain implements ValidatorFactory {

    public static final CreateUnitValidator CREATE_UNIT_VALIDATOR = new CreateUnitValidator();
    public static final FindAllUnitsValidator FIND_ALL_VALIDATOR = new FindAllUnitsValidator();
    public static final UpdateUnitValidator UPDATE_UNIT_VALIDATOR = new UpdateUnitValidator();

    @Override
    public Validator make(String validatorName) {
        if("CreateUnitValidator".equals(validatorName))
            return CREATE_UNIT_VALIDATOR;
        else if("FindAllUnitsValidator".equals(validatorName))
            return FIND_ALL_VALIDATOR;
        else if("UpdateUnitValidator".equals(validatorName))
            return UPDATE_UNIT_VALIDATOR;
        return null;
    }

}
