package com.cleancrud.interactor.unit;

import com.cleancrud.interactor.Callback;
import com.cleancrud.interactor.Interactor;
import com.cleancrud.interactor.input.Input;
import com.cleancrud.interactor.output.Output;

public class CreateUnitInteractor implements Interactor {
    @Override
    public Callback execute(Input input) {
        Output output = new Output();
        return new Callback(output);
    }
}
