package com.cleancrud.test.interactor;

import com.cleancrud.domain.Unit;
import com.cleancrud.gateway.UnitGateway;
import com.cleancrud.interactor.impl.unit.CreateUnitInteractor;
import com.cleancrud.test.interactor.factory.InputFactoryDozer;
import com.cleancrud.test.interactor.factory.ValidatorFactoryPlain;
import com.cleancrud.interactor.input.CreateUnitInput;
import com.cleancrud.interactor.mapper.MapperDozer;
import com.cleancrud.interactor.output.CreateUnitOutput;
import com.skeleton.interactor.Interactor;
import com.skeleton.interactor.exception.Code;
import com.skeleton.interactor.exception.InputException;
import com.skeleton.interactor.factory.InteractorFactory;
import com.skeleton.interactor.factory.ValidatorFactory;
import com.skeleton.interactor.input.Input;
import com.skeleton.interactor.mapper.Mapper;
import com.skeleton.interactor.validator.Validator;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class UnitCreateTest {
    private static final Logger logger = Logger.getLogger(UnitCreateTest.class);
    private InputFactoryDozer inputFactory;
    private InteractorFactory interactorFactory;
    private UnitGateway unitGateway;
    private ValidatorFactory validatorFactory = new ValidatorFactoryPlain();
    private MapperDozer mapperDozer = new MapperDozer(new DozerBeanMapper());
    @Before
    public void setup(){
        inputFactory = new InputFactoryDozer(new DozerBeanMapper());
        setupUnitGateway();
        setupInteractorFactory();
    }

    private void setupInteractorFactory() {
        CreateUnitInteractor createUnitInteractor = new CreateUnitInteractor();
        createUnitInteractor.setUnitGateway(unitGateway);
        createUnitInteractor.setMapper(mapperDozer);
        createUnitInteractor.setValidator(validatorFactory.make("CreateUnitValidator"));
        interactorFactory = mock(InteractorFactory.class);
        when(interactorFactory.make(anyString())).thenReturn(createUnitInteractor);
    }

    private void setupUnitGateway(){
        unitGateway = mock(UnitGateway.class);
        setupUnitGatewayCreate();
    }

    private void setupUnitGatewayCreate() {
        doAnswer((Answer<Void>) invocation -> {
            Unit unit = invocation.getArgumentAt(0,Unit.class);
            Double random = Math.random() * 10;
            unit.setId(random.longValue());
            return null;
        }).when(unitGateway).create(any(Unit.class));
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
        Interactor interactor = interactorFactory.make("CreateUnitInteractor");
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
        Interactor interactor = interactorFactory.make("CreateUnitInteractor");
        interactor.execute(input)
                .then(output -> {
                    CreateUnitOutput createUnitOutput = (CreateUnitOutput)output;
                    logger.debug(createUnitOutput.getId()+" - "+createUnitOutput.getDescription());
                    assertTrue(createUnitOutput.getId() != null);
                });
    }
}
