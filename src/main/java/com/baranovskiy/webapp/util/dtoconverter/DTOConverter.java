package com.baranovskiy.webapp.util.dtoconverter;

import com.baranovskiy.webapp.model.*;

import java.util.ArrayList;
import java.util.List;


public abstract class DTOConverter<T extends BaseModel, DTO> {

    public abstract DTO toDTO(T model);

    public abstract T toModel(DTO dto);

    public  List<DTO> dtoList(List<T> modelList) {
        List<DTO> dtoList = new ArrayList<>();
        for (T model : modelList) {
            dtoList.add(toDTO(model));
        }
        return dtoList;
    }

}
