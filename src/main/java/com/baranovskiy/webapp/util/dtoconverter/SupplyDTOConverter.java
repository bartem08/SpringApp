package com.baranovskiy.webapp.util.dtoconverter;

import com.baranovskiy.webapp.model.Distributor;
import com.baranovskiy.webapp.model.Product;
import com.baranovskiy.webapp.model.Supply;
import com.baranovskiy.webapp.model.dto.SupplyDTO;
import com.baranovskiy.webapp.model.fields.Quality;
import com.baranovskiy.webapp.repository.Operable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SupplyDTOConverter extends DTOConverter<Supply, SupplyDTO> {

    @Autowired
    @Qualifier("hbnDistributorDAO")
    private Operable<Distributor> distributorDAO;

    @Autowired
    @Qualifier("hbnProductDAO")
    private Operable<Product> productDAO;

    @Override
    public SupplyDTO toDTO(Supply model) {
        return new SupplyDTO(model.getID(), model.getDistributor().getName(),
                model.getProduct().getName(), model.getQuality().toString(),
                model.getPrice().toString());
    }

    @Override
    public Supply toModel(SupplyDTO supplyDTO) {
        Supply supply = new Supply();
        supply.setDistributor(distributorDAO.findByName(supplyDTO.getDistributorName()));
        supply.setProduct(productDAO.findByName(supplyDTO.getProductName()));
        supply.setQuality(Quality.valueOf(supplyDTO.getQuality()));
        supply.setPrice(Float.parseFloat(supplyDTO.getPrice()));
        supply.setID(supplyDTO.getID());
        return supply;
    }

}
