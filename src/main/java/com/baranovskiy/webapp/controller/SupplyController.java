package com.baranovskiy.webapp.controller;

import com.baranovskiy.webapp.model.Distributor;
import com.baranovskiy.webapp.model.Product;
import com.baranovskiy.webapp.model.Supply;
import com.baranovskiy.webapp.model.dto.SupplyDTO;
import com.baranovskiy.webapp.model.fields.Quality;
import com.baranovskiy.webapp.repository.Operable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping("/linked")
public class SupplyController {

    private static final Logger LOG = LogManager.getLogger(SupplyController.class);

    @Autowired
    @Qualifier("hbnDistributorDAO")
    Operable<Distributor> distributorDAO;
    @Autowired
    @Qualifier("hbnProductDAO")
    Operable<Product> productDAO;
    @Autowired
    @Qualifier("hbnSupplyDAO")
    Operable<Supply> supplyDAO;

    @RequestMapping(value = "/distributor/{d_id}", method = RequestMethod.GET)
    public String allByDistributor(@PathVariable("d_id") Integer d_id, Map<String, Object> map) {

        Distributor distributor = distributorDAO.findByID(d_id);
        LOG.info(String.format("Distributor %s was successfully found", distributor.getName()));
        map.put("supplyDTO", new SupplyDTO());
        map.put("distributor", distributor);
        map.put("productList", productDAO.getAll());
        map.put("qualityList", Quality.values());
        map.put("supplyList", distributor.getProducts());
        return "linked/distributor/supply";

    }

    @RequestMapping(value = {"/distributor/{d_id}/save-or-update", "/distributor/{d_id}/update/supply"},
                    method = RequestMethod.POST)
    public String saveOrUpdateDistributor(@ModelAttribute("supplyDTO") SupplyDTO supplyDTO,
                               @PathVariable("d_id") Integer d_id) {

        Supply supply = new Supply(distributorDAO.findByName(supplyDTO.getDistributorName()),
                                   productDAO.findByName(supplyDTO.getProductName()),
                                   Quality.valueOf(supplyDTO.getQuality()),
                                   supplyDTO.getPrice());
        supply.setID(supplyDTO.getID());

        if (supplyDTO.getID() == null) {
            supplyDAO.add(supply);
            LOG.info(String.format("Supply %s was successfully inserted", supply.getName()));
        } else {
            supplyDAO.update(supply);
            LOG.info(String.format("Supply %s was successfully updated", supply.getName()));
        }
        return String.format("redirect:/linked/distributor/%d", d_id);

    }

    @RequestMapping(value = "/distributor/{d_id}/delete/{s_id}")
    public String delete(@PathVariable("d_id") Integer d_id,
                         @PathVariable("s_id") Integer s_id) {

        supplyDAO.delete(s_id);
        LOG.info("Supply was successfully deleted");
        return String.format("redirect:/linked/distributor/%d", d_id);

    }

    @RequestMapping(value = "/distributor/{d_id}/update/supply")
    public String updateForm(@PathVariable("d_id") Integer d_id,
                             @RequestParam("s_id") Integer s_id,
                             Map<String, Object> map) {

        Distributor distributor = distributorDAO.findByID(d_id);
        Supply supply = supplyDAO.findByID(s_id);
        map.put("supplyDTO", new SupplyDTO(supply.getID(), supply.getDistributor().getName(),
                supply.getProduct().getName(), supply.getQuality().toString(), supply.getPrice()));
        map.put("productList", productDAO.getAll());
        map.put("qualityList", Quality.values());
        map.put("distributor", distributor);
        map.put("supplyList", distributor.getProducts());
        return "linked/distributor/supply";

    }

}
