package com.baranovskiy.webapp.controller.web;

import com.baranovskiy.webapp.model.Product;
import com.baranovskiy.webapp.model.dto.ProductDTO;
import com.baranovskiy.webapp.model.fields.Category;
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
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOG = LogManager.getLogger(ProductController.class);

    private final String VIEW = "product";

    @Autowired
    @Qualifier("hbnProductDAO")
    private Operable<Product> dao;

    @Autowired
    @Qualifier("productDTOConverter")
    private DTOConverter<Product, ProductDTO> converter;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String productList(Map<String, Object> map) {
        putModelToTheView(map, new ProductDTO(), true);
        return VIEW;
    }

    @RequestMapping(value = {"/save-or-update", "/update/product"}, method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("product") @Valid ProductDTO dto,
                               BindingResult result, Map<String, Object> map) {
        if (result.hasErrors()) {
            LOG.error(result.getFieldError().getDefaultMessage());
            putModelToTheView(map, dto, false);
            return VIEW;
        }
        if (dto.getID() == null) {
            dao.add(converter.toModel(dto));
        } else {
            dao.update(converter.toModel(dto));
        }
        return "redirect:/product/all";
    }

    @RequestMapping(value = "/delete/{productID}")
    public String delete(@PathVariable("productID") Integer id) {
        dao.delete(id);
        LOG.info("Product was successfully deleted");
        return "redirect:/product/all";
    }

    @RequestMapping(value = "/update/product")
    public String updateForm(@RequestParam("id") Integer id, Map<String, Object> map) {
        putModelToTheView(map, converter.toDTO(dao.findByID(id)), true);
        return VIEW;
    }

    private void putModelToTheView(Map<String, Object> map, ProductDTO dto, boolean putDTO) {
        if (putDTO) {
            map.put("product", dto);
        }
        map.put("productList", converter.toDTO(dao.getAll()));
        map.put("categoryList", Category.values());
    }

}
