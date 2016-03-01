package com.baranovskiy.webapp.controller.rest;

import com.baranovskiy.webapp.model.BaseModel;
import com.baranovskiy.webapp.repository.Operable;
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
    public ResponseEntity<List<DTO>> getAll() {
        LOG.info("Attempt to fetch all.");
        List<T> models = dao.getAll();
        if (models.isEmpty()) {
            LOG.error("No model in the repository.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(converter.toDTO(models), HttpStatus.OK);
    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    public ResponseEntity<DTO> getByID(@PathVariable("ID") Integer id) {
        LOG.info(String.format("Fetching model with id %d.", id));
        T model = dao.findByID(id);
        if (model == null) {
            LOG.error(String.format("Model with id %d not found.", id));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        DTO dto = converter.toDTO(model);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> add() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> update() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.DELETE)
    public ResponseEntity<T> delete(@PathVariable("ID") Integer id) {
        LOG.info(String.format("Deleting distributor with id %d.", id));
        if (dao.findByID(id) == null) {
            LOG.error(String.format("Model with id %d not found.", id));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dao.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
