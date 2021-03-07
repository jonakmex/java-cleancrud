package com.cleancrud.interactor.factory.impl;

import com.cleancrud.interactor.factory.InputFactory;
import com.cleancrud.interactor.input.CreateUnitInput;
import com.cleancrud.interactor.input.Input;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;

import java.util.Map;

@AllArgsConstructor
public class InputFactoryDozer implements InputFactory {

    private Mapper mapper;

    public Input make (String inputName, Map<String,Object> params){
        if("CreateUnitInput".equals(inputName))
            return makeCreateUnitInput(params);

        return null;
    }

    private  Input makeCreateUnitInput(Map<String, Object> params) {
        return mapper.map(params, CreateUnitInput.class);
    }

}
