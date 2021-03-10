package com.skeleton.interactor.factory;



import com.skeleton.interactor.Interactor;

import java.util.Map;

public interface InteractorFactory {
    Interactor make(String interactorName, Map<String,Object> params);
}
