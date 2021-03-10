package com.cleancrud.interactor.mapper;

import com.skeleton.interactor.mapper.Mapper;

public class MapperDozer implements Mapper {
    private org.dozer.Mapper dozer;

    public MapperDozer(org.dozer.Mapper dozer) {
        this.dozer = dozer;
    }

    @Override
    public <T> T map(Object o, Class<T> aClass) {
        return dozer.map(o,aClass);
    }
}
