package com.cleancrud.interactor.factory;

import com.cleancrud.interactor.Interactor;

import java.util.Map;

public interface InteractorFactory {
    Interactor make(String interactorName, Map<String,Object> params);
}
