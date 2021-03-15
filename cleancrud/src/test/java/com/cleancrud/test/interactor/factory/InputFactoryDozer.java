package com.cleancrud.test.interactor.factory;


import com.cleancrud.interactor.input.CreateUnitInput;
import com.cleancrud.interactor.input.FindAllInput;
import com.cleancrud.interactor.input.UpdateUnitInput;
import com.skeleton.interactor.factory.InputFactory;
import com.skeleton.interactor.input.Input;
import lombok.Data;
import org.dozer.Mapper;

import java.util.Map;

@Data
public class InputFactoryDozer implements InputFactory {

    private Mapper mapper;

    public InputFactoryDozer(Mapper mapper) {
        this.mapper = mapper;
    }

    public Input make (String inputName, Map<String,Object> params){
        if("CreateUnitInput".equals(inputName))
            return makeCreateUnitInput(params);
        else if("FindAllInput".equals(inputName))
            return makeFindAllInput(params);
        else if("UpdateUnitInput".equals(inputName))
            return makeUpdateUnitInput(params);

        return null;
    }

    private Input makeUpdateUnitInput(Map<String, Object> params) {
        return mapper.map(params, UpdateUnitInput.class);
    }

    private Input makeFindAllInput(Map<String, Object> params) {
        return mapper.map(params, FindAllInput.class);
    }

    private  Input makeCreateUnitInput(Map<String, Object> params) {
        return mapper.map(params, CreateUnitInput.class);
    }

}
