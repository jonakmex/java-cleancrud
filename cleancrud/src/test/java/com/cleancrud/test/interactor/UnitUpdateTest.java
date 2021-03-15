package com.cleancrud.test.interactor;

import com.cleancrud.domain.Unit;
import com.cleancrud.gateway.UnitGateway;
import com.cleancrud.interactor.impl.unit.UpdateUnitInteractor;
import com.cleancrud.interactor.mapper.MapperDozer;
import com.cleancrud.interactor.output.UpdateUnitOutput;
import com.cleancrud.test.interactor.factory.InputFactoryDozer;
import com.cleancrud.test.interactor.factory.ValidatorFactoryPlain;
import com.skeleton.interactor.Interactor;
import com.skeleton.interactor.exception.Code;
import com.skeleton.interactor.exception.InputException;
import com.skeleton.interactor.factory.InteractorFactory;
import com.skeleton.interactor.factory.ValidatorFactory;
import com.skeleton.interactor.input.Input;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class UnitUpdateTest {
    private static final Logger logger = Logger.getLogger(UnitUpdateTest.class);

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
        UpdateUnitInteractor updateUnitInteractor = new UpdateUnitInteractor();
        updateUnitInteractor.setUnitGateway(unitGateway);
        updateUnitInteractor.setMapper(mapperDozer);
        updateUnitInteractor.setValidator(validatorFactory.make("UpdateUnitValidator"));
        interactorFactory = mock(InteractorFactory.class);
        when(interactorFactory.make(anyString())).thenReturn(updateUnitInteractor);
    }

    private void setupUnitGateway(){
        unitGateway = Mockito.mock(UnitGateway.class);
        setupUnitGatewayUpdate();
    }

    private void setupUnitGatewayUpdate() {
       Unit unit = new Unit();
       unit.setId(1L);
       unit.setDescription("Current");
       doAnswer(invocation -> invocation.getArgumentAt(0,Unit.class)).when(unitGateway).update(any(Unit.class));
       when(unitGateway.findById(1L)).thenReturn(unit);
       when(unitGateway.findById(2L)).thenReturn(null);
    }


    @Test
    public void success_update(){
        Map<String,Object> params = new HashMap<>();
        params.put("id",1);
        params.put("description","Hello World");
        Input updateUnitInput = inputFactory.make("UpdateUnitInput",params);
        Interactor interactor = interactorFactory.make("UpdateUnitInteractor");
        interactor.execute(updateUnitInput)
                .then(output -> {
                    UpdateUnitOutput updateUnitOutput = (UpdateUnitOutput) output;
                    assertTrue(updateUnitOutput.success);
                })
                .onError(ex -> {throw ex;});
    }

    @Test
    public void not_existent_update(){
        Map<String,Object> params = new HashMap<>();
        params.put("id",2);
        params.put("description","Hello World");
        Input updateUnitInput = inputFactory.make("UpdateUnitInput",params);
        Interactor interactor = interactorFactory.make("UpdateUnitInteractor");
        interactor.execute(updateUnitInput)
                .then(output -> {
                    UpdateUnitOutput updateUnitOutput = (UpdateUnitOutput) output;
                    assertTrue(!updateUnitOutput.success);
                })
                .onError(ex -> {throw ex;});
    }

    @Test
    public void input_not_valid_update(){
        Map<String,Object> params = new HashMap<>();
        params.put("id",1);
        params.put("description","He");
        Input updateUnitInput = inputFactory.make("UpdateUnitInput",params);
        Interactor interactor = interactorFactory.make("UpdateUnitInteractor");
        interactor.execute(updateUnitInput)
                .then(output -> {
                    assertTrue(1==0);
                })
                .onError(ex -> {
                    InputException inputException = (InputException)ex;
                    assertTrue(inputException.code.equals(Code.ERR_INVALID_INPUT));
                });
    }

}
