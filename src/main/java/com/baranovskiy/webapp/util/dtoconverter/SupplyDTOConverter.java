package com.baranovskiy.webapp.util.dtoconverter;

import com.baranovskiy.webapp.model.Supply;
import com.baranovskiy.webapp.model.dto.SupplyDTO;
import com.baranovskiy.webapp.model.fields.Quality;
import org.springframework.stereotype.Component;

@Component
public class SupplyDTOConverter extends DTOConverter<Supply, SupplyDTO> {

    @Override
    public SupplyDTO toDTO(Supply model) {
        return new SupplyDTO(model.getID(), model.getDistributor().getName(),
                model.getProduct().getName(), model.getQuality().toString(), model.getPrice());
    }

    @Override
    public Supply toModel(SupplyDTO supplyDTO) {
        Supply supply = new Supply();
        supply.setQuality(Quality.valueOf(supplyDTO.getQuality()));
        supply.setPrice(supplyDTO.getPrice());
        supply.setID(supplyDTO.getID());
        return supply;
    }

}
