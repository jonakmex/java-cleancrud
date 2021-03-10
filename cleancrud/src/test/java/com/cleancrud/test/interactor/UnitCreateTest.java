package com.cleancrud.test.interactor;

import com.cleancrud.gateway.UnitGateway;
import com.cleancrud.interactor.factory.InputFactoryDozer;
import com.cleancrud.interactor.factory.InteractorFactoryPlain;
import com.cleancrud.interactor.factory.ValidatorFactoryPlain;
import com.cleancrud.interactor.input.CreateUnitInput;
import com.cleancrud.interactor.mapper.MapperDozer;
import com.cleancrud.interactor.output.CreateUnitOutput;
import com.skeleton.interactor.Interactor;
import com.skeleton.interactor.exception.Code;
import com.skeleton.interactor.exception.InputException;
import com.skeleton.interactor.factory.ValidatorFactory;
import com.skeleton.interactor.input.Input;
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
    private MapperDozer mapperDozer = new MapperDozer(new DozerBeanMapper());
    @Before
    public void setup(){
        inputFactory = new InputFactoryDozer(new DozerBeanMapper());
        interactorFactory = new InteractorFactoryPlain();
        unitGateway = unit -> unit.setId(1L);
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
        Interactor interactor = getInteractor();
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
        Interactor interactor = getInteractor();
        interactor.execute(input)
                .then(output -> {
                    assertTrue(1 == 0);
                })
                .onError(exception -> {
                    InputException inputException = (InputException) exception;
                    assertTrue(Code.ERR_INVALID_INPUT.equals(inputException.code) );
                    assertTrue("1".equals(inputException.errors.get("description").get("length")));
                });
    }

    @Test
    public void create_unit_success(){
        Map<String,Object> params = new HashMap<>();
        params.put("description","Hola Mundo");
        Input input = inputFactory.make("CreateUnitInput",params);
        Interactor interactor = getInteractor();
        interactor.execute(input)
                .then(output -> {
                    CreateUnitOutput createUnitOutput = (CreateUnitOutput)output;
                    logger.debug(createUnitOutput.getId()+" - "+createUnitOutput.getDescription());
                    assertTrue(createUnitOutput.getId() == 1);
                });
    }

    private Interactor getInteractor() {
        Map<String,Object> interactorParams = new HashMap<>();
        interactorParams.put("unitGateway",unitGateway);
        interactorParams.put("validator",validatorFactory.make("CreateUnitValidator"));
        interactorParams.put("mapper",mapperDozer);
        Interactor interactor = interactorFactory.make("CreateUnitInteractor",interactorParams);
        return interactor;
    }

}
