package com.baranovskiy.webapp.util.dtoconverter;

import com.baranovskiy.webapp.model.Product;
import com.baranovskiy.webapp.model.dto.ProductDTO;
import com.baranovskiy.webapp.model.fields.Category;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOConverter extends DTOConverter<Product, ProductDTO> {

    @Override
    public ProductDTO toDTO(Product model) {
        return new ProductDTO(model.getID(), model.getName(), model.getCategory().toString());
    }

    @Override
    public Product toModel(ProductDTO productDTO) {
        Product product = new Product(productDTO.getName(), Category.valueOf(productDTO.getCategory()));
        product.setID(productDTO.getID());
        return product;
    }

}

