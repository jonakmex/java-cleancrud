package com.cleancrud.interactor;

import com.cleancrud.interactor.input.CreateUnitInput;
import com.cleancrud.interactor.input.Input;
import org.dozer.Mapper;

import java.util.Map;

public class InputFactory {

    private Mapper mapper;

    public InputFactory(Mapper mapper) {
        this.mapper = mapper;
    }

    public  Input make (String inputName, Map<String,Object> params){
        if("CreateUnitInput".equals(inputName))
            return makeCreateUnitInput(params);

        return null;
    }

    private  Input makeCreateUnitInput(Map<String, Object> params) {
        return mapper.map(params,CreateUnitInput.class);
    }

}
