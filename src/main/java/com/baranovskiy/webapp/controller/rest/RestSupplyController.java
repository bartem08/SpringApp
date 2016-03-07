package com.baranovskiy.webapp.controller.rest;

import com.baranovskiy.webapp.controller.AbstractRestController;
import com.baranovskiy.webapp.controller.Filler;
import com.baranovskiy.webapp.controller.Response;
import com.baranovskiy.webapp.model.Distributor;
import com.baranovskiy.webapp.model.Product;
import com.baranovskiy.webapp.controller.ResponseJSON;
import com.baranovskiy.webapp.model.Supply;
import com.baranovskiy.webapp.model.dto.SupplyDTO;
import com.baranovskiy.webapp.repository.Operable;
import com.baranovskiy.webapp.util.dtoconverter.DTOConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/supply")
public class RestSupplyController extends AbstractRestController<Supply, SupplyDTO> {

    private static final Logger LOG = LogManager.getLogger(RestSupplyController.class);

    @Autowired
    @Qualifier("hbnDistributorDAO")
    private Operable<Distributor> distributorDAO;

    @Autowired
    @Qualifier("hbnProductDAO")
    private Operable<Product> productDAO;

    public RestSupplyController() {}

    @Autowired
    public RestSupplyController(@Qualifier("hbnSupplyDAO") Operable<Supply> dao,
                                @Qualifier("supplyDTOConverter") DTOConverter<Supply, SupplyDTO> converter) {
        super(dao, converter);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ResponseJSON> saveOrUpdate(@RequestBody @Valid SupplyDTO supplyDTO, BindingResult result) {
        if (result.hasErrors()) {
            LOG.error(result.getFieldError().getDefaultMessage());
            return Response.createResponse(result, HttpStatus.BAD_REQUEST);
        }
        if (distributorDAO.findByName(supplyDTO.getDistributorName()) == null ||
                productDAO.findByName(supplyDTO.getProductName()) == null) {
            LOG.error(Filler.Message.NONEXISTENT_MODEL);
            return Response.createResponse(Filler.Message.NONEXISTENT_MODEL, HttpStatus.NOT_FOUND);
        }
        return save(supplyDTO);
    }

}
