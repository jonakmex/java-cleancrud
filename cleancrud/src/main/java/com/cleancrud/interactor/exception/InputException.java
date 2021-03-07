package com.cleancrud.interactor.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class InputException extends RuntimeException {
    public String code;
    public Map<String,Map<String,String>> errors;
}
