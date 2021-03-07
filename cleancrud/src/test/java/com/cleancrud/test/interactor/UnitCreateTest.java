package com.cleancrud.test.interactor;

import com.cleancrud.interactor.InputFactory;
import com.cleancrud.interactor.input.CreateUnitInput;
import com.cleancrud.interactor.input.Input;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class UnitCreateTest {
    private static final Logger logger = Logger.getLogger(UnitCreateTest.class);
    private InputFactory inputFactory;
    @Before
    public void setup(){
        inputFactory = new InputFactory(new DozerBeanMapper());
    }

    @Test
    public void create_person_input(){
        Map<String,Object> params = new HashMap<>();
        params.put("description","Hello World");
        Input input = inputFactory.make("CreateUnitInput",params);
        CreateUnitInput createUnitInput = (CreateUnitInput) input;
        assertTrue("Hello World".equals(createUnitInput.description));
    }


}
