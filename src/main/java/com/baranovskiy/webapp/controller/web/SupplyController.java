package com.baranovskiy.webapp.controller.web;

import com.baranovskiy.webapp.controller.Filler;
import com.baranovskiy.webapp.model.Distributor;
import com.baranovskiy.webapp.model.Product;
import com.baranovskiy.webapp.model.Supply;
import com.baranovskiy.webapp.model.dto.SupplyDTO;
import com.baranovskiy.webapp.model.fields.Quality;
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
@RequestMapping(Filler.Url.SUPPLY)
public class SupplyController {

    private static final Logger LOG = LogManager.getLogger(SupplyController.class);

    @Autowired
    @Qualifier("hbnDistributorDAO")
    private Operable<Distributor> distributorDAO;

    @Autowired
    @Qualifier("hbnProductDAO")
    private Operable<Product> productDAO;

    @Autowired
    @Qualifier("hbnSupplyDAO")
    private Operable<Supply> supplyDAO;

    @Autowired
    @Qualifier("supplyDTOConverter")
    private DTOConverter<Supply, SupplyDTO> converter;

    private void saveOrUpdate(SupplyDTO dto) {
        Supply supply = converter.toModel(dto);
        supply.setDistributor(distributorDAO.findByName(dto.getDistributorName()));
        supply.setProduct(productDAO.findByName(dto.getProductName()));
        if (dto.getID() == null) {
            supplyDAO.add(supply);
        } else {
            supplyDAO.update(supply);
        }
    }

    private void putModelByDistributorToTheView(Map<String, Object> map,
                                                Distributor distributor,
                                                SupplyDTO dto) {
        if (dto != null) {
            dto.setDistributorName(distributor.getName());
            map.put("supply", dto);
        }
        map.put("distributorID", distributor.getID());
        map.put("qualityList", Quality.values());
        map.put("productList", productDAO.getAll());
        map.put("supplyList", converter.toDTO(distributor.getProducts()));
    }

    private void putModelByProductToTheView(Map<String, Object> map,
                                            Product product,
                                            SupplyDTO dto) {
        if (dto != null) {
            dto.setProductName(product.getName());
            map.put("supply", dto);
        }
        map.put("productID", product.getID());
        map.put("qualityList", Quality.values());
        map.put("distributorList", distributorDAO.getAll());
        map.put("supplyList", converter.toDTO(product.getDistributors()));
    }

    @RequestMapping(value = "/distributor/{d_id}", method = RequestMethod.GET)
    public String allByDistributor(@PathVariable("d_id") Integer d_id, Map<String, Object> map) {
        Distributor distributor = distributorDAO.findByID(d_id);
        putModelByDistributorToTheView(map, distributor, new SupplyDTO());
        return Filler.View.SUPPLY_DISTRIBUTOR_VIEW;
    }

    @RequestMapping(value = "/product/{p_id}", method = RequestMethod.GET)
    public String allByProduct(@PathVariable("p_id") Integer d_id, Map<String, Object> map) {
        Product product = productDAO.findByID(d_id);
        putModelByProductToTheView(map, product, new SupplyDTO());
        return Filler.View.SUPPLY_PRODUCT_VIEW;
    }

    @RequestMapping(value = {"/distributor/{d_id}/save-or-update", "/distributor/{d_id}/update/supply"},
                    method = RequestMethod.POST)
    public String saveOrUpdateByDistributor(@ModelAttribute("supply") @Valid SupplyDTO dto,
                                            BindingResult result,
                                            @PathVariable("d_id") Integer d_id,
                                            Map<String, Object> map) {
        if (result.hasErrors()) {
            LOG.error(result.getFieldError().getDefaultMessage());
            putModelByDistributorToTheView(map, distributorDAO.findByID(d_id), null);
            return Filler.View.SUPPLY_DISTRIBUTOR_VIEW;
        }
        saveOrUpdate(dto);
        return String.format("redirect:/linked/distributor/%d", d_id);
    }

    @RequestMapping(value = {"/product/{p_id}/save-or-update", "/product/{p_id}/update/supply"},
            method = RequestMethod.POST)
    public String saveOrUpdateByProduct(@ModelAttribute("supply") @Valid SupplyDTO dto,
                                        BindingResult result,
                                        @PathVariable("p_id") Integer d_id,
                                        Map<String, Object> map) {
        if (result.hasErrors()) {
            LOG.error(result.getFieldError().getDefaultMessage());
            putModelByProductToTheView(map, productDAO.findByID(d_id), null);
            return Filler.View.SUPPLY_PRODUCT_VIEW;
        }
        saveOrUpdate(dto);
        return String.format("redirect:/linked/product/%d", d_id);
    }

    @RequestMapping(value = "/distributor/{d_id}/delete/{s_id}")
    public String deleteByDistributor(@PathVariable("d_id") Integer d_id,
                                      @PathVariable("s_id") Integer s_id) {
        supplyDAO.delete(s_id);
        return String.format("redirect:/linked/distributor/%d", d_id);
    }

    @RequestMapping(value = "/product/{p_id}/delete/{s_id}")
    public String deleteByProduct(@PathVariable("p_id") Integer p_id,
                                  @PathVariable("s_id") Integer s_id) {
        supplyDAO.delete(s_id);
        return String.format("redirect:/linked/product/%d", p_id);
    }

    @RequestMapping(value = "/distributor/{d_id}/update/supply")
    public String updateDistributorForm(@PathVariable("d_id") Integer d_id,
                             @RequestParam("s_id") Integer s_id,
                             Map<String, Object> map) {
        Distributor distributor = distributorDAO.findByID(d_id);
        putModelByDistributorToTheView(map, distributor, converter.toDTO(supplyDAO.findByID(s_id)));
        return Filler.View.SUPPLY_DISTRIBUTOR_VIEW;
    }

    @RequestMapping(value = "/product/{p_id}/update/supply")
    public String updateProductForm(@PathVariable("p_id") Integer p_id,
                                        @RequestParam("s_id") Integer s_id,
                                        Map<String, Object> map) {
        Product product = productDAO.findByID(p_id);
        putModelByProductToTheView(map, product, converter.toDTO(supplyDAO.findByID(s_id)));
        return Filler.View.SUPPLY_PRODUCT_VIEW;
    }

}
