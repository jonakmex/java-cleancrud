package com.cleancrud.test.interactor;

import com.cleancrud.domain.Unit;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UnitCreateTest {
    private static final Logger logger = Logger.getLogger(UnitCreateTest.class);
    @Test
    public void create_person(){
        Unit unit = new Unit();
        unit.setDescription("Casa 10");
        assertTrue("Casa 10".equals(unit.getDescription()));
    }
}
