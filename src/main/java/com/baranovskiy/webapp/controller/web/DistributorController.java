package com.baranovskiy.webapp.controller.web;

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
@RequestMapping("/distributor")
public class DistributorController {

    private static final Logger LOG = LogManager.getLogger(DistributorController.class);

    private final String VIEW = "distributor";

    @Autowired
    @Qualifier("hbnDistributorDAO")
    private Operable<Distributor> dao;

    @Autowired
    @Qualifier("distributorDTOConverter")
    private DTOConverter<Distributor, DistributorDTO> converter;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll(Map<String, Object> map) {
        putModelToTheView(map, new DistributorDTO());
        return VIEW;
    }

    @RequestMapping(value = {"/save-or-update", "/update/distributor"}, method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("distributor") @Valid DistributorDTO dto,
                               BindingResult result, Map<String, Object> map) {
        if (result.hasErrors()) {
            LOG.error(result.getFieldError().getDefaultMessage());
            map.put("distributorList", converter.toDTO(dao.getAll()));
            return VIEW;
        }
        if (dto.getID() == null) {
            dao.add(converter.toModel(dto));
            LOG.info("Distributor {} was successfully inserted", dto.getName());
        } else {
            dao.update(converter.toModel(dto));
            LOG.info("Distributor {} was successfully updated", dto.getName());
        }
        return "redirect:/distributor/all";
    }

    @RequestMapping(value = "/delete/{distributorID}")
    public String delete(@PathVariable("distributorID") Integer id) {
        dao.delete(id);
        LOG.info("Distributor was successfully deleted");
        return "redirect:/distributor/all";
    }

    @RequestMapping(value = "/update/distributor")
    public String updateForm(@RequestParam("id") Integer id, Map<String, Object> map) {
        putModelToTheView(map, converter.toDTO(dao.findByID(id)));
        return VIEW;
    }

    private void putModelToTheView(Map<String, Object> map, DistributorDTO dto) {
        map.put("distributor", dto);
        map.put("distributorList", converter.toDTO(dao.getAll()));
    }

}
