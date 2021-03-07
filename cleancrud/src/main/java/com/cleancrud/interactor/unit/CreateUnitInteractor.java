package com.cleancrud.interactor.unit;

import com.cleancrud.gateway.UnitGateway;
import com.cleancrud.interactor.Callback;
import com.cleancrud.interactor.Interactor;
import com.cleancrud.interactor.input.Input;
import com.cleancrud.interactor.output.CreateUnitOutput;
import com.cleancrud.interactor.validator.Validator;

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
