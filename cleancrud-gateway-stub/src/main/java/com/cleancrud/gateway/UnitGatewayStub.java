package com.cleancrud.gateway;

import com.cleancrud.domain.Unit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UnitGatewayStub implements UnitGateway{
    private static final int NUM_UNITS = 5;

    @Override
    public void create(Unit unit) {
        Double random = Math.random() * 10;
        unit.setId(random.longValue());
    }

    @Override
    public void update(Unit unit) {

    }

    @Override
    public Unit findById(Long id) {
        Unit unit = new Unit();
        unit.setId(1L);
        unit.setDescription("Current");
        switch (id.intValue()){
            case 1:
                return unit;
            default:
                return null;
        }
    }

    @Override
    public List<Unit> findAll() {
        List<Unit> unitList = new ArrayList<>();
        for (int i = 0; i < NUM_UNITS; i++) {
            Unit unit = new Unit();
            unit.setId(Long.valueOf(i));
            unit.setDescription("Stub Unit - "+i);
            unitList.add(unit);
        }
        return unitList;
    }
}
