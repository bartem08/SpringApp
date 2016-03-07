package com.baranovskiy.webapp.controller;

import com.baranovskiy.webapp.model.BaseModel;
import com.baranovskiy.webapp.model.dto.BaseDTO;
import com.baranovskiy.webapp.repository.Operable;
import com.baranovskiy.webapp.util.dtoconverter.DTOConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractWebController<T extends BaseModel, DTO extends BaseDTO> {

    private static final Logger LOG = LogManager.getLogger(AbstractWebController.class);

    protected Operable<T> dao;

    protected DTOConverter<T, DTO> converter;

    public AbstractWebController(Operable<T> dao, DTOConverter<T, DTO> converter) {
        this.dao = dao;
        this.converter = converter;
    }

    public void save(DTO dto) {
        try {
            if (dto.getID() == null) {
                dao.add(converter.toModel(dto));
            } else {
                dao.update(converter.toModel(dto));
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
    }

}
