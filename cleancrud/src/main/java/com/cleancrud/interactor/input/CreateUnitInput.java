package com.cleancrud.interactor.input;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CreateUnitInput extends Input{
    public String description;
}
