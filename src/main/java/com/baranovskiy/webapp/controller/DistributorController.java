package com.baranovskiy.webapp.controller;

import com.baranovskiy.webapp.model.Distributor;
import com.baranovskiy.webapp.repository.Operable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/distributor")
public class DistributorController {

    private static final Logger LOG = LogManager.getLogger(DistributorController.class);

    @Autowired
    @Qualifier("hbnDistributorDAO")
    private Operable<Distributor> dao;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String distributorList(Map<String, Object> map) {
        map.put("distributor", new Distributor());
        map.put("distributorList", dao.getAll());
        return "distributor";
    }

    @RequestMapping(value = {"/save-or-update", "/update/distributor"}, method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("distributor") Distributor distributor) {
        if (distributor.getID() == null) {
            dao.add(distributor);
            LOG.info(String.format("Distributor %s was successfully inserted", distributor.getName()));
        } else {
            dao.update(distributor);
            LOG.info(String.format("Distributor %s was successfully updated", distributor.getName()));
        }
        return "redirect:/distributor/all";
    }

    @RequestMapping(value = "/delete/{distributorID}")
    public String delete(@PathVariable("distributorID") Integer id) {
        dao.delete(id);
        LOG.info("Distributor was successfully inserted");
        return "redirect:/distributor/all";
    }

    @RequestMapping(value = "/update/distributor")
    public String updateForm(@RequestParam("id") Integer id, Map<String, Object> map) {
        map.put("distributor", dao.findByID(id));
        map.put("distributorList", dao.getAll());
        return "distributor";
    }

}
