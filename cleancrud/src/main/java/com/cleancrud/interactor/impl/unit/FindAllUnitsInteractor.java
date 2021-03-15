package com.cleancrud.interactor.impl.unit;

import com.cleancrud.gateway.UnitGateway;
import com.cleancrud.interactor.ds.unit.UnitDS;
import com.cleancrud.interactor.output.FindAllOutput;
import com.skeleton.interactor.Callback;
import com.skeleton.interactor.Interactor;
import com.skeleton.interactor.input.Input;
import com.skeleton.interactor.mapper.Mapper;
import com.skeleton.interactor.validator.Validator;
import lombok.Data;
import lombok.Setter;

import java.util.stream.Collectors;
@Data
@Setter
public class FindAllUnitsInteractor implements Interactor {

    private UnitGateway unitGateway;
    private Mapper mapper;
    private Validator validator;

    public FindAllUnitsInteractor(UnitGateway unitGateway, Mapper mapper, Validator validator) {
        this.unitGateway = unitGateway;
        this.mapper = mapper;
        this.validator = validator;
    }

    public FindAllUnitsInteractor() {
        super();
    }

    @Override
    public Callback execute(Input input) {
        try{
            validator.validate(input);
            FindAllOutput output = new FindAllOutput();
            output.units =  unitGateway.findAll()
                    .stream()
                    .map(unit -> mapper.map(unit,UnitDS.class))
                    .collect(Collectors.toList());

            return new Callback(output);
        }
        catch (RuntimeException e){
            return new Callback(e);
        }

    }
}
