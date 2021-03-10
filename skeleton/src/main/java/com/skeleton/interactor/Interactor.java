package com.skeleton.interactor;


import com.skeleton.interactor.input.Input;

public interface Interactor {
    Callback execute(Input input);
}
