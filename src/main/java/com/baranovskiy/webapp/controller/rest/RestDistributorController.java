package com.baranovskiy.webapp.controller.rest;

import com.baranovskiy.webapp.model.Distributor;
import com.baranovskiy.webapp.model.dto.DistributorDTO;
import com.baranovskiy.webapp.repository.Operable;
import com.baranovskiy.webapp.util.dtoconverter.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/distributor")
public class RestDistributorController extends AbstractRestController<Distributor, DistributorDTO> {

    public RestDistributorController() {}

    @Autowired
    public RestDistributorController(@Qualifier("hbnDistributorDAO") Operable<Distributor> dao,
                                     @Qualifier("distributorDTOConverter") DTOConverter<Distributor, DistributorDTO> converter) {
        super(dao, converter);
    }


}
