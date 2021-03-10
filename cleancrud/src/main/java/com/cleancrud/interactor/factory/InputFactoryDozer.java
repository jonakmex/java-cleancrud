package com.cleancrud.interactor.factory;


import com.cleancrud.interactor.input.CreateUnitInput;

import com.cleancrud.interactor.input.FindAllInput;
import com.skeleton.interactor.factory.InputFactory;
import com.skeleton.interactor.input.Input;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;

import java.util.Map;

@AllArgsConstructor
public class InputFactoryDozer implements InputFactory {

    private Mapper mapper;

    public Input make (String inputName, Map<String,Object> params){
        if("CreateUnitInput".equals(inputName))
            return makeCreateUnitInput(params);
        else if("FindAllInput".equals(inputName))
            return makeFindAllInput(params);

        return null;
    }

    private Input makeFindAllInput(Map<String, Object> params) {
        return mapper.map(params, FindAllInput.class);
    }

    private  Input makeCreateUnitInput(Map<String, Object> params) {
        return mapper.map(params, CreateUnitInput.class);
    }

}
