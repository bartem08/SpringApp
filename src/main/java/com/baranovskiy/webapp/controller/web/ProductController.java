package com.baranovskiy.webapp.controller.web;

import com.baranovskiy.webapp.controller.AbstractWebController;
import com.baranovskiy.webapp.controller.Filler;
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
@RequestMapping(Filler.Url.PRODUCT)
public class ProductController extends AbstractWebController<Product, ProductDTO> {

    private static final Logger LOG = LogManager.getLogger(ProductController.class);

    @Autowired
    public ProductController(@Qualifier("hbnProductDAO") Operable<Product> dao,
                             @Qualifier("productDTOConverter") DTOConverter<Product, ProductDTO> converter) {
        super(dao, converter);
    }

    private void putModelToTheView(Map<String, Object> map, ProductDTO dto) {
        if (dto != null) {
            map.put("product", dto);
        }
        map.put("productList", converter.toDTO(dao.getAll()));
        map.put("categoryList", Category.values());
    }

    @RequestMapping(value = Filler.Url.ALL, method = RequestMethod.GET)
    public String productList(Map<String, Object> map) {
        putModelToTheView(map, new ProductDTO());
        return Filler.View.PRODUCT_VIEW;
    }

    @RequestMapping(
            value = {Filler.Url.SAVE_OR_UPDATE, Filler.Url.PRODUCT_UPDATE},
            method = RequestMethod.POST
    )
    public String saveOrUpdate(@ModelAttribute("product") @Valid ProductDTO dto,
                               BindingResult result, Map<String, Object> map) {
        if (result.hasErrors()) {
            LOG.error(result.getFieldError().getDefaultMessage());
            putModelToTheView(map, null);
            return Filler.View.PRODUCT_VIEW;
        }
        super.save(dto);
        putModelToTheView(map, new ProductDTO());
        return Filler.View.PRODUCT_VIEW;
    }

    @RequestMapping(value = "/delete/{productID}")
    public String delete(@PathVariable("productID") Integer id,
                         Map<String, Object> map) {
        dao.delete(id);
        putModelToTheView(map, new ProductDTO());
        return Filler.View.PRODUCT_VIEW;
    }

    @RequestMapping(value = Filler.Url.PRODUCT_UPDATE)
    public String updateForm(@RequestParam("id") Integer id, Map<String, Object> map) {
        putModelToTheView(map, converter.toDTO(dao.findByID(id)));
        return Filler.View.PRODUCT_VIEW;
    }

}
