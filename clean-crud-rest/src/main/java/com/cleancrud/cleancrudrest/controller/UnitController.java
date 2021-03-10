package com.cleancrud.cleancrudrest.controller;

import com.cleancrud.cleancrudrest.payload.ds.unit.UnitVw;
import com.cleancrud.cleancrudrest.payload.response.unit.FindAllResponse;
import com.cleancrud.interactor.output.FindAllOutput;
import com.skeleton.interactor.Interactor;
import com.skeleton.interactor.factory.InputFactory;
import com.skeleton.interactor.factory.InteractorFactory;
import com.skeleton.interactor.input.Input;
import org.dozer.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/units")
public class UnitController {

    private final InteractorFactory interactorFactory;
    private final InputFactory inputFactory;
    private final Mapper restMapper;

    public UnitController(InteractorFactory interactorFactory, InputFactory inputFactory, Mapper restMapper) {
        this.interactorFactory = interactorFactory;
        this.inputFactory = inputFactory;
        this.restMapper = restMapper;
    }

    @GetMapping("/findAll")
    public ResponseEntity<FindAllResponse> findAll(){
        FindAllResponse findAllResponse = new FindAllResponse();
        Interactor interactor = interactorFactory.make("FindAllInteractor",null);
        Input input = inputFactory.make("",null);

        interactor.execute(input)
                .then(output -> {
                    FindAllOutput findAllOutput = (FindAllOutput) output;
                    List<UnitVw> units = findAllOutput.units
                            .stream()
                            .map(unitDS -> restMapper.map(unitDS,UnitVw.class))
                            .collect(Collectors.toList());
                    findAllResponse.units = units;
                });
        return ResponseEntity.ok(findAllResponse);

    }
}
