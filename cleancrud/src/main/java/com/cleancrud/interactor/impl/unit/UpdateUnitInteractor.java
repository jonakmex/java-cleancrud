package com.cleancrud.interactor.impl.unit;

import com.cleancrud.domain.Unit;
import com.cleancrud.gateway.UnitGateway;
import com.cleancrud.interactor.input.UpdateUnitInput;
import com.cleancrud.interactor.output.UpdateUnitOutput;
import com.skeleton.interactor.Callback;
import com.skeleton.interactor.Interactor;
import com.skeleton.interactor.input.Input;
import com.skeleton.interactor.mapper.Mapper;
import com.skeleton.interactor.validator.Validator;


public class UpdateUnitInteractor implements Interactor {
    private UnitGateway unitGateway;
    private Validator validator;
    private Mapper mapper;

    public UpdateUnitInteractor(UnitGateway unitGateway, Validator validator, Mapper mapper) {
        this.unitGateway = unitGateway;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Callback execute(Input input) {
        try{
            validator.validate(input);
            UpdateUnitInput updateUnitInput = (UpdateUnitInput) input;
            UpdateUnitOutput updateUnitOutput = new UpdateUnitOutput();
            Unit current = unitGateway.findById(updateUnitInput.id);
            if(current != null){
                current.setDescription(updateUnitInput.description);
                unitGateway.update(current);
                updateUnitOutput.success = Boolean.TRUE;
            }
            else
                updateUnitOutput.success = Boolean.FALSE;

            return new Callback(updateUnitOutput);
        }
        catch (RuntimeException e){
            return new Callback(e);
        }

    }
}
