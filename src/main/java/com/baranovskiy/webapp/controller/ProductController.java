package com.baranovskiy.webapp.controller;

import com.baranovskiy.webapp.model.Product;
import com.baranovskiy.webapp.model.fields.Category;
import com.baranovskiy.webapp.repository.Operable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOG = LogManager.getLogger(ProductController.class);

    @Autowired
    @Qualifier("hbnProductDAO")
    private Operable<Product> dao;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String productList(Map<String, Object> map) {
        map.put("product", new Product());
        map.put("productList", dao.getAll());
        map.put("categoryList", Category.values());
        return "product";
    }

    @RequestMapping(value = {"/save-or-update", "/update/product"}, method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("product") Product product) {
        if (product.getID() == null) {
            dao.add(product);
            LOG.info(String.format("Product %s was successfully inserted", product.getName()));
        } else {
            dao.update(product);
            LOG.info(String.format("Product %s was successfully updated", product.getName()));
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
        map.put("product", dao.findByID(id));
        map.put("categoryList", Category.values());
        map.put("productList", dao.getAll());
        return "product";
    }

}
