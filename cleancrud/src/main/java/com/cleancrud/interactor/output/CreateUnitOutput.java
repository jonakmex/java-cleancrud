package com.cleancrud.interactor.output;

import com.skeleton.interactor.output.Output;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CreateUnitOutput extends Output {
    public Long id;
    public String description;
}
