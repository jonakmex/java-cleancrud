package com.skeleton.interactor.factory;



import com.skeleton.interactor.input.Input;

import java.util.Map;

public interface InputFactory {
    Input make (String inputName, Map<String,Object> params);
}
