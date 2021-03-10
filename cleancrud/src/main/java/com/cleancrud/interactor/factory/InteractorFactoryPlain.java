package com.cleancrud.interactor.factory;

import com.cleancrud.gateway.UnitGateway;

import com.cleancrud.interactor.impl.unit.CreateUnitInteractor;
import com.cleancrud.interactor.impl.unit.FindAllUnitsInteractor;
import com.cleancrud.interactor.impl.unit.UpdateUnitInteractor;
import com.skeleton.interactor.Interactor;
import com.skeleton.interactor.factory.InteractorFactory;
import com.skeleton.interactor.mapper.Mapper;
import com.skeleton.interactor.validator.Validator;


import java.util.Map;

public class InteractorFactoryPlain implements InteractorFactory {
    public Interactor make(String interactorName, Map<String,Object> params){
        if("CreateUnitInteractor".equals(interactorName))
            return makeCreateUnitInteractor(params);
        else if("FindAllInteractor".equals(interactorName))
            return makeFindAllInteractor(params);
        else if("UpdateUnitInteractor".equals(interactorName))
            return makeUpdateUnitInteractor(params);
        return null;
    }

    private Interactor makeUpdateUnitInteractor(Map<String, Object> params) {
        UnitGateway unitGateway = (UnitGateway) params.get("unitGateway");
        Mapper mapper = (Mapper) params.get("mapper");
        Validator validator = (Validator) params.get("validator");
        return new UpdateUnitInteractor(unitGateway,validator,mapper);
    }

    private Interactor makeFindAllInteractor(Map<String, Object> params) {
        UnitGateway unitGateway = (UnitGateway) params.get("unitGateway");
        Mapper mapper = (Mapper) params.get("mapper");
        Validator validator = (Validator) params.get("validator");
        return new FindAllUnitsInteractor(unitGateway,mapper,validator);
    }

    private Interactor makeCreateUnitInteractor(Map<String, Object> params) {
        UnitGateway unitGateway = (UnitGateway) params.get("unitGateway");
        Validator validator = (Validator) params.get("validator");
        Mapper mapper = (Mapper) params.get("mapper");
        return new CreateUnitInteractor(unitGateway,validator,mapper);
    }
}
