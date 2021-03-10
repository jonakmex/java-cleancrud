package com.skeleton.interactor.mapper;

public interface Mapper {
    <T> T map(Object source, Class<T> destinationClass);
}
