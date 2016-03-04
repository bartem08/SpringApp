package com.baranovskiy.webapp.controller.web;

import com.baranovskiy.webapp.controller.Filler;
import com.baranovskiy.webapp.model.Distributor;
import com.baranovskiy.webapp.model.dto.DistributorDTO;
import com.baranovskiy.webapp.repository.Operable;
import com.baranovskiy.webapp.util.dtoconverter.DTOConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Map;

@Controller
@RequestMapping(Filler.Url.DISTRIBUTOR)
public class DistributorController {

    private static final Logger LOG = LogManager.getLogger(DistributorController.class);

    @Autowired
    @Qualifier("hbnDistributorDAO")
    private Operable<Distributor> dao;

    @Autowired
    @Qualifier("distributorDTOConverter")
    private DTOConverter<Distributor, DistributorDTO> converter;

    private void putModelToTheView(Map<String, Object> map, DistributorDTO dto) {
        if (dto != null) {
            map.put("distributor", dto);
        }
        map.put("distributorList", converter.toDTO(dao.getAll()));
    }

    @RequestMapping(value = Filler.Url.ALL, method = RequestMethod.GET)
    public String getAll(Map<String, Object> map) {
        putModelToTheView(map, new DistributorDTO());
        return Filler.View.DISTRIBUTOR_VIEW;
    }

    @RequestMapping(
            value = {Filler.Url.SAVE_OR_UPDATE, Filler.Url.DISTRIBUTOR_UPDATE},
            method = RequestMethod.POST
    )
    public String saveOrUpdate(@ModelAttribute("distributor") @Valid DistributorDTO dto,
                               BindingResult result, Map<String, Object> map) {
        if (result.hasErrors()) {
            LOG.error(result.getFieldError().getDefaultMessage());
            putModelToTheView(map, null);
            return Filler.View.DISTRIBUTOR_VIEW;
        }
        if (dto.getID() == null) {
            dao.add(converter.toModel(dto));
        } else {
            dao.update(converter.toModel(dto));
        }
        putModelToTheView(map, new DistributorDTO());
        return Filler.View.DISTRIBUTOR_VIEW;
    }

    @RequestMapping(value = "/delete/{distributorID}")
    public String delete(@PathVariable("distributorID") Integer id,
                         Map<String, Object> map) {
        dao.delete(id);
        putModelToTheView(map, new DistributorDTO());
        return Filler.View.DISTRIBUTOR_VIEW;
    }

    @RequestMapping(value = Filler.Url.DISTRIBUTOR_UPDATE)
    public String updateForm(@RequestParam("id") Integer id, Map<String, Object> map) {
        putModelToTheView(map, converter.toDTO(dao.findByID(id)));
        return Filler.View.DISTRIBUTOR_VIEW;
    }

}
