package com.baranovskiy.webapp.util.dtoconverter;

import com.baranovskiy.webapp.model.entity.Product;
import com.baranovskiy.webapp.model.entity.Supply;
import com.baranovskiy.webapp.model.dto.ProductDTO;
import com.baranovskiy.webapp.model.dto.SupplyDTO;
import com.baranovskiy.webapp.model.fields.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOConverter extends DTOConverter<Product, ProductDTO> {

    @Autowired
    @Qualifier("supplyDTOConverter")
    private DTOConverter<Supply, SupplyDTO> converter;

    @Override
    public ProductDTO toDTO(Product model) {
        return new ProductDTO(model.getID(), model.getName(), model.getCategory().toString(),
                converter.toDTO(model.getDistributors()));
    }

    @Override
    public Product toModel(ProductDTO productDTO) {
        Product product = new Product(productDTO.getName(), Category.valueOf(productDTO.getCategory()));
        product.setID(productDTO.getID());
        return product;
    }

}

