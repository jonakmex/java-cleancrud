package com.cleancrud.gateway;

import com.cleancrud.domain.Unit;

import java.util.List;

public interface UnitGateway {
    void create(Unit unit);
    void update(Unit unit);
    Unit findById(Long id);
    List<Unit> findAll();
}
