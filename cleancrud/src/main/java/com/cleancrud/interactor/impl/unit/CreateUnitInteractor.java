package com.cleancrud.interactor.impl.unit;

import com.cleancrud.gateway.UnitGateway;
import com.cleancrud.interactor.output.CreateUnitOutput;
import com.skeleton.interactor.Callback;
import com.skeleton.interactor.Interactor;
import com.skeleton.interactor.input.Input;
import com.skeleton.interactor.validator.Validator;


public class CreateUnitInteractor implements Interactor {
    private UnitGateway unitGateway;
    private Validator validator;

    public CreateUnitInteractor(UnitGateway unitGateway, Validator validator) {
        this.unitGateway = unitGateway;
        this.validator = validator;
    }

    @Override
    public Callback execute(Input input) {
        CreateUnitOutput output = new CreateUnitOutput();
        try{
           validator.validate(input);
           output.id = 1L;
           return new Callback(output);
        }
        catch (RuntimeException e){
            return new Callback(e);
        }

    }
}
