package com.cleancrud.cleancrudrest.payload.response.unit;

import com.cleancrud.cleancrudrest.payload.ds.unit.UnitVw;
import com.cleancrud.cleancrudrest.payload.response.Response;
import lombok.Data;

import java.util.List;
@Data
public class FindAllResponse extends Response {
    public List<UnitVw> units;
}
