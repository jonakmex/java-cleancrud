package com.cleancrud.interactor;

import com.cleancrud.interactor.unit.CreateUnitInteractor;

public class InteractorFactory {
    public  Interactor make(String interactorName){
        if("CreateUnitInteractor".equals(interactorName))
            return new CreateUnitInteractor();
        return null;
    }
}
