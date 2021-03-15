package com.cleancrud.test.interactor;

import com.cleancrud.domain.Unit;
import com.cleancrud.gateway.UnitGateway;
import com.cleancrud.interactor.ds.unit.UnitDS;
import com.cleancrud.interactor.impl.unit.FindAllUnitsInteractor;
import com.cleancrud.interactor.mapper.MapperDozer;
import com.cleancrud.interactor.output.FindAllOutput;
import com.cleancrud.test.interactor.factory.InputFactoryDozer;
import com.cleancrud.test.interactor.factory.ValidatorFactoryPlain;
import com.skeleton.interactor.Interactor;
import com.skeleton.interactor.factory.InteractorFactory;
import com.skeleton.interactor.factory.ValidatorFactory;
import com.skeleton.interactor.input.Input;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnitListTest {
    private static final Logger logger = Logger.getLogger(UnitListTest.class);
    private static final int NUM_UNITS = 10;

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
        FindAllUnitsInteractor findAllUnitsInteractor = new FindAllUnitsInteractor();
        findAllUnitsInteractor.setUnitGateway(unitGateway);
        findAllUnitsInteractor.setMapper(mapperDozer);
        findAllUnitsInteractor.setValidator(validatorFactory.make("FindAllUnitsValidator"));
        interactorFactory = mock(InteractorFactory.class);
        when(interactorFactory.make(anyString())).thenReturn(findAllUnitsInteractor);
    }

    private void setupUnitGateway(){
        unitGateway = Mockito.mock(UnitGateway.class);
        setupUnitGatewayFindAll();
    }

    private void setupUnitGatewayFindAll() {
        List<Unit> unitList = new ArrayList<>();
        for (int i = 0; i < NUM_UNITS; i++) {
            Unit unit = new Unit();
            unit.setId(Long.valueOf(i));
            unit.setDescription("Unidad - "+i);
            unitList.add(unit);
        }
        when(unitGateway.findAll()).thenReturn(unitList);
    }

    @Test
    public void find_all_units(){
        Map<String,Object> params = new HashMap<>();
        Input input = inputFactory.make("FindAllInput",params);
        Interactor interactor = interactorFactory.make("FindAllUnitsInteractor");
        interactor.execute(input)
        .then(output -> {
            FindAllOutput findAllOutput = (FindAllOutput) output;
            for (UnitDS unitDs: findAllOutput.units) {
                logger.debug(unitDs.id + " - " +unitDs.description);
            }
            assertTrue(findAllOutput.units.size() == NUM_UNITS);
        })
        .onError(ex -> {
            throw ex;
        });
    }
}
