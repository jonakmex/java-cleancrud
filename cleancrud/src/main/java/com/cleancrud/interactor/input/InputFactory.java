package com.cleancrud.interactor.input;

import lombok.AllArgsConstructor;
import org.dozer.Mapper;

import java.util.Map;

@AllArgsConstructor
public class InputFactory {

    private Mapper mapper;

    public  Input make (String inputName, Map<String,Object> params){
        if("CreateUnitInput".equals(inputName))
            return makeCreateUnitInput(params);

        return null;
    }

    private  Input makeCreateUnitInput(Map<String, Object> params) {
        return mapper.map(params,CreateUnitInput.class);
    }

}
