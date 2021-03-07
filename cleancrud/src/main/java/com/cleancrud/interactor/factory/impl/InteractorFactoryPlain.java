package com.cleancrud.interactor.factory.impl;

import com.cleancrud.gateway.UnitGateway;
import com.cleancrud.interactor.Interactor;
import com.cleancrud.interactor.factory.InteractorFactory;
import com.cleancrud.interactor.unit.CreateUnitInteractor;
import com.cleancrud.interactor.validator.Validator;

import java.util.Map;

public class InteractorFactoryPlain implements InteractorFactory {
    public Interactor make(String interactorName, Map<String,Object> params){
        if("CreateUnitInteractor".equals(interactorName))
            return makeCreateUnitInteractor(params);
        return null;
    }

    private Interactor makeCreateUnitInteractor(Map<String, Object> params) {
        UnitGateway unitGateway = (UnitGateway) params.get("unitGateway");
        Validator validator = (Validator) params.get("validator");
        return new CreateUnitInteractor(unitGateway,validator);
    }
}
