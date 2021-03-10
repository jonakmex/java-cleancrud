package com.cleancrud.interactor.input;

import com.skeleton.interactor.input.Input;
import lombok.Data;

@Data
public class UpdateUnitInput extends Input {
    public Long id;
    public String description;
}
