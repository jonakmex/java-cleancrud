package com.skeleton.interactor.factory;


import com.skeleton.interactor.Interactor;

public interface InteractorFactory {
    Interactor make(String interactorName);
}
