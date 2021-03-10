package com.cleancrud.cleancrudrest.factory;

import com.skeleton.interactor.Interactor;
import com.skeleton.interactor.factory.InteractorFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service("interactorFactory")
public class InteractorFactorySpring implements InteractorFactory {

    private final ApplicationContext context;

    public InteractorFactorySpring(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Interactor make(String s, Map<String, Object> map) {
        return (Interactor) context.getBean(s);
    }
}
