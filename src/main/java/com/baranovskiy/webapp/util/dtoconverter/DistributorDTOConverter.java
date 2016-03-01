package com.baranovskiy.webapp.util.dtoconverter;

import com.baranovskiy.webapp.model.Distributor;
import com.baranovskiy.webapp.model.dto.DistributorDTO;
import org.springframework.stereotype.Component;

@Component
public class DistributorDTOConverter extends DTOConverter<Distributor, DistributorDTO> {

    @Override
    public DistributorDTO toDTO(Distributor model) {
        return new DistributorDTO(model.getID(), model.getName(), model.getCooperatedDate());
    }

    @Override
    public Distributor toModel(DistributorDTO distributorDTO) {
        Distributor distributor = new Distributor(distributorDTO.getName());
        distributor.setID(distributorDTO.getID());
        return distributor;
    }

}
