package com.cleancrud.interactor.factory;

import com.cleancrud.interactor.validator.Validator;

public interface ValidatorFactory {
    Validator make(String validatorName);
}
