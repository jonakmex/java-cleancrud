package com.cleancrud.test.interactor;

import com.cleancrud.domain.Unit;
import com.cleancrud.gateway.UnitGateway;
import com.cleancrud.interactor.Interactor;
import com.cleancrud.interactor.exception.Code;
import com.cleancrud.interactor.exception.InputException;
import com.cleancrud.interactor.factory.ValidatorFactory;
import com.cleancrud.interactor.factory.impl.InteractorFactoryPlain;
import com.cleancrud.interactor.factory.impl.ValidatorFactoryPlain;
import com.cleancrud.interactor.input.CreateUnitInput;
import com.cleancrud.interactor.input.Input;
import com.cleancrud.interactor.factory.impl.InputFactoryDozer;
import com.cleancrud.interactor.output.CreateUnitOutput;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class UnitCreateTest {
    private static final Logger logger = Logger.getLogger(UnitCreateTest.class);
    private InputFactoryDozer inputFactory;
    private InteractorFactoryPlain interactorFactory;
    private UnitGateway unitGateway;
    private ValidatorFactory validatorFactory = new ValidatorFactoryPlain();
    @Before
    public void setup(){
        inputFactory = new InputFactoryDozer(new DozerBeanMapper());
        interactorFactory = new InteractorFactoryPlain();
        unitGateway = unit -> new Unit();
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
        Map<String,Object> interactorParams = new HashMap<>();
        interactorParams.put("unitGateway",unitGateway);
        interactorParams.put("validator",validatorFactory.make("CreateUnitValidator"));
        Interactor interactor = interactorFactory.make("CreateUnitInteractor",interactorParams);
        interactor.execute(input)
                .then(output -> {
                    CreateUnitOutput createUnitOutput = (CreateUnitOutput) output;
                    assertTrue(createUnitOutput.id != null);
                })
                .onError(exception -> {
                    assertTrue(1 == 0);
                });
    }

    @Test
    public void create_unit_interactor_exception(){
        Map<String,Object> params = new HashMap<>();
        params.put("description","H");
        Input input = inputFactory.make("CreateUnitInput",params);
        Map<String,Object> interactorParams = new HashMap<>();
        interactorParams.put("unitGateway",unitGateway);
        interactorParams.put("validator",validatorFactory.make("CreateUnitValidator"));
        Interactor interactor = interactorFactory.make("CreateUnitInteractor",interactorParams);
        interactor.execute(input)
                .then(output -> {
                    assertTrue(1 == 0);
                })
                .onError(exception -> {
                    InputException inputException = (InputException) exception;
                    logger.debug(inputException.code);
                    assertTrue(Code.ERR_INVALID_INPUT.equals(inputException.code) );
                    assertTrue("1".equals(inputException.errors.get("description").get("length")));
                });
    }

}
