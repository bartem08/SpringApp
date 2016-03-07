package com.baranovskiy.webapp.controller;

import com.baranovskiy.webapp.model.BaseModel;
import com.baranovskiy.webapp.model.ResponseJSON;
import com.baranovskiy.webapp.repository.Operable;
import com.baranovskiy.webapp.util.ResponseFormer;
import com.baranovskiy.webapp.util.dtoconverter.DTOConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public abstract class AbstractRestController<T extends BaseModel, DTO> {

    private static final Logger LOG = LogManager.getLogger(AbstractRestController.class);

    protected Operable<T> dao;

    protected DTOConverter<T, DTO> converter;

    public AbstractRestController() {}

    public AbstractRestController(Operable<T> dao, DTOConverter<T, DTO> converter) {
        this.dao = dao;
        this.converter = converter;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        List<T> models = dao.getAll();
        if (models.isEmpty()) {
            LOG.error(Filler.Message.EMPTY_REPOSITORY);
            return ResponseFormer.createResponse(Filler.Message.EMPTY_REPOSITORY, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(converter.toDTO(models), HttpStatus.OK);
    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    public ResponseEntity getByID(@PathVariable("ID") Integer id) {
        T model = dao.findByID(id);
        if (model == null) {
            LOG.error(Filler.Message.MODEL_NOT_FOUND);
            return ResponseFormer.createResponse(Filler.Message.MODEL_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(converter.toDTO(model), HttpStatus.OK);
    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseJSON> delete(@PathVariable("ID") Integer id) {
        if (dao.findByID(id) == null) {
            LOG.error(Filler.Message.MODEL_NOT_FOUND);
            return ResponseFormer.createResponse(Filler.Message.MODEL_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        dao.delete(id);
        return ResponseFormer.createResponse(Filler.Message.SUCCESS_DELETE, HttpStatus.OK);
    }

    protected ResponseEntity<ResponseJSON> save(DTO dto) {
        T model = converter.toModel(dto);
        try {
            if (dao.findByID(model.getID()) == null) {
                dao.add(model);
            } else {
                dao.update(model);
            }
        } catch (Exception ex) {
            return ResponseFormer.createResponse("already exists", HttpStatus.CONFLICT);
        }
        return ResponseFormer.createResponse(Filler.Message.SUCCESS_SAVE, HttpStatus.OK);
    }

}
