package com.baranovskiy.webapp.controller.rest;

import com.baranovskiy.webapp.controller.AbstractRestController;
import com.baranovskiy.webapp.controller.Response;
import com.baranovskiy.webapp.model.Distributor;
import com.baranovskiy.webapp.controller.ResponseJSON;
import com.baranovskiy.webapp.model.dto.DistributorDTO;
import com.baranovskiy.webapp.repository.Operable;
import com.baranovskiy.webapp.util.dtoconverter.DTOConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/rest/distributor")
public class RestDistributorController extends AbstractRestController<Distributor, DistributorDTO> {

    private static final Logger LOG = LogManager.getLogger(RestDistributorController.class);

    public RestDistributorController() {}

    @Autowired
    public RestDistributorController(@Qualifier("hbnDistributorDAO") Operable<Distributor> dao,
                                     @Qualifier("distributorDTOConverter") DTOConverter<Distributor, DistributorDTO> converter) {
        super(dao, converter);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ResponseJSON> saveOrUpdate(@RequestBody @Valid DistributorDTO distributorDTO, BindingResult result) {
        if (result.hasErrors()) {
            LOG.error(result.getFieldError().getDefaultMessage());
            return Response.createResponse(result, HttpStatus.CONFLICT);
        }
        return save(distributorDTO);
    }

}
