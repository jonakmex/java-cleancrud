package com.skeleton.interactor.factory;


import com.skeleton.interactor.validator.Validator;

public interface ValidatorFactory {
    Validator make(String validatorName);
}
