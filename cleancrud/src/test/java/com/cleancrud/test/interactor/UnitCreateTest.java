package com.cleancrud.test.interactor;

import com.cleancrud.test.interactor.input.CreateUnitInput;
import com.cleancrud.test.interactor.input.Input;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class UnitCreateTest {
    private static final Logger logger = Logger.getLogger(UnitCreateTest.class);
    @Test
    public void create_person_input(){
        Map<String,Object> params = new HashMap<>();
        params.put("description","Hello World");
        Input input = InputFactory.make("CreateUnitInput",params);
        CreateUnitInput createUnitInput = (CreateUnitInput) input;
        assertTrue("Hello World".equals(createUnitInput.description));
    }

 
}
