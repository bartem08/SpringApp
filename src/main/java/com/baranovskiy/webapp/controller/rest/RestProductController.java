package com.baranovskiy.webapp.controller.rest;

import com.baranovskiy.webapp.controller.AbstractRestController;
import com.baranovskiy.webapp.util.ResponseFormer;
import com.baranovskiy.webapp.model.entity.Product;
import com.baranovskiy.webapp.model.ResponseJSON;
import com.baranovskiy.webapp.model.dto.ProductDTO;
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
@RequestMapping("/rest/product")
public class RestProductController extends AbstractRestController<Product, ProductDTO> {

    private static final Logger LOG = LogManager.getLogger(RestProductController.class);

    public RestProductController() {}

    @Autowired
    public RestProductController(@Qualifier("hbnProductDAO") Operable<Product> dao,
                                 @Qualifier("productDTOConverter") DTOConverter<Product, ProductDTO> converter) {
        super(dao, converter);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ResponseJSON> saveOrUpdate(@RequestBody @Valid ProductDTO productDTO, BindingResult result) {
        if (result.hasErrors()) {
            LOG.error(result.getFieldError().getDefaultMessage());
            return ResponseFormer.createResponse(result, HttpStatus.BAD_REQUEST);
        }
        return save(productDTO);
    }

}
