package com.cleancrud.interactor.impl.unit;

import com.cleancrud.domain.Unit;
import com.cleancrud.gateway.UnitGateway;
import com.cleancrud.interactor.output.CreateUnitOutput;
import com.skeleton.interactor.Callback;
import com.skeleton.interactor.Interactor;
import com.skeleton.interactor.input.Input;
import com.skeleton.interactor.mapper.Mapper;
import com.skeleton.interactor.validator.Validator;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class CreateUnitInteractor implements Interactor {
    private UnitGateway unitGateway;
    private Validator validator;
    private Mapper mapper;

    public CreateUnitInteractor(UnitGateway unitGateway, Validator validator, Mapper mapper) {
        this.unitGateway = unitGateway;
        this.validator = validator;
        this.mapper = mapper;
    }

    public CreateUnitInteractor() {
        super();
    }

    @Override
    public Callback execute(Input input) {
        try{
           validator.validate(input);
           Unit unit = mapper.map(input,Unit.class);
           unitGateway.create(unit);
           CreateUnitOutput output = mapper.map(unit,CreateUnitOutput.class);
           return new Callback(output);
        }
        catch (RuntimeException e){
            return new Callback(e);
        }
    }
}
