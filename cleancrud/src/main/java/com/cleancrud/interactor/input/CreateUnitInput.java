package com.cleancrud.interactor.input;

import com.skeleton.interactor.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CreateUnitInput extends Input {
    public String description;
}
