package com.cleancrud.interactor;

import com.cleancrud.interactor.input.Input;

public interface Interactor {
    Callback execute(Input input);
}
