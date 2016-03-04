package com.baranovskiy.webapp.util.dtoconverter;

import com.baranovskiy.webapp.model.Distributor;
import com.baranovskiy.webapp.model.Supply;
import com.baranovskiy.webapp.model.dto.DistributorDTO;
import com.baranovskiy.webapp.model.dto.SupplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DistributorDTOConverter extends DTOConverter<Distributor, DistributorDTO> {

    @Autowired
    @Qualifier("supplyDTOConverter")
    private DTOConverter<Supply, SupplyDTO> converter;

    @Override
    public DistributorDTO toDTO(Distributor model) {
        return new DistributorDTO(model.getID(), model.getName(), model.getCooperatedDate(),
                converter.toDTO(model.getProducts()));
    }

    @Override
    public Distributor toModel(DistributorDTO distributorDTO) {
        Distributor distributor = new Distributor(distributorDTO.getName());
        distributor.setID(distributorDTO.getID());
        return distributor;
    }

}
