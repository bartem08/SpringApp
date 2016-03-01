package com.baranovskiy.webapp.controller.rest;

import com.baranovskiy.webapp.model.Distributor;
import com.baranovskiy.webapp.model.Product;
import com.baranovskiy.webapp.model.dto.DistributorDTO;
import com.baranovskiy.webapp.model.dto.ProductDTO;
import com.baranovskiy.webapp.repository.Operable;
import com.baranovskiy.webapp.util.DateConverter;
import com.baranovskiy.webapp.util.dtoconverter.DTOConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
