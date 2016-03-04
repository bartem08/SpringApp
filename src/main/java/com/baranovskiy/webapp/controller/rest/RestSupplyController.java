package com.baranovskiy.webapp.controller.rest;

import com.baranovskiy.webapp.controller.AbstractRestController;
import com.baranovskiy.webapp.model.Supply;
import com.baranovskiy.webapp.model.dto.SupplyDTO;
import com.baranovskiy.webapp.repository.Operable;
import com.baranovskiy.webapp.util.dtoconverter.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/supply")
public class RestSupplyController extends AbstractRestController<Supply, SupplyDTO> {

    public RestSupplyController() {}

    @Autowired
    public RestSupplyController(@Qualifier("hbnSupplyDAO") Operable<Supply> dao,
                                @Qualifier("supplyDTOConverter") DTOConverter<Supply, SupplyDTO> converter) {
        super(dao, converter);
    }

}
