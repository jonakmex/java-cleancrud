package com.cleancrud.interactor.factory;

import com.cleancrud.interactor.input.Input;

import java.util.Map;

public interface InputFactory {
    Input make (String inputName, Map<String,Object> params);
}
