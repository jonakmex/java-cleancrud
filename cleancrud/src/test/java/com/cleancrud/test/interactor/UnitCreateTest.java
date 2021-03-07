package com.cleancrud.test.interactor;

import com.cleancrud.interactor.Interactor;
import com.cleancrud.interactor.InteractorFactory;
import com.cleancrud.interactor.input.InputFactory;
import com.cleancrud.interactor.input.CreateUnitInput;
import com.cleancrud.interactor.input.Input;
import com.cleancrud.interactor.output.Output;
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
    private InteractorFactory interactorFactory;
    private Interactor createInputInteractor;
    @Before
    public void setup(){
        inputFactory = new InputFactory(new DozerBeanMapper());
        interactorFactory = new InteractorFactory();
    }

    @Test
    public void create_person_input(){
        Map<String,Object> params = new HashMap<>();
        params.put("description","Hello World");
        Input input = inputFactory.make("CreateUnitInput",params);
        CreateUnitInput createUnitInput = (CreateUnitInput) input;
        assertTrue("Hello World".equals(createUnitInput.description));
    }
    @Test
    public void create_person_interactor(){
        Map<String,Object> params = new HashMap<>();
        params.put("description","Hello World");
        Input input = inputFactory.make("CreateUnitInput",params);
        Interactor interactor = interactorFactory.make("CreateUnitInteractor");
        interactor.execute(input)
                .then(output -> {
                    assertTrue(output != null);
                })
                .onError(exception -> {
                    
                });
    }

}
