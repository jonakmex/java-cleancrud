package com.cleancrud.interactor.factory;

import com.cleancrud.gateway.UnitGateway;

import com.cleancrud.interactor.impl.unit.CreateUnitInteractor;
import com.skeleton.interactor.Interactor;
import com.skeleton.interactor.factory.InteractorFactory;
import com.skeleton.interactor.mapper.Mapper;
import com.skeleton.interactor.validator.Validator;


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
        Mapper mapper = (Mapper) params.get("mapper");
        return new CreateUnitInteractor(unitGateway,validator,mapper);
    }
}
