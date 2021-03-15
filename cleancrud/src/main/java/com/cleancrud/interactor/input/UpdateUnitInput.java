package com.cleancrud.interactor.input;

import com.skeleton.interactor.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UpdateUnitInput extends Input {
    public Long id;
    public String description;
}
