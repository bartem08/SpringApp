package com.baranovskiy.webapp.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Product data transfer object.
 *
 * @see com.baranovskiy.webapp.model.BaseModel
 * @see com.baranovskiy.webapp.model.Distributor
 * @version 1.0
 * @author Baranovskiy Artem
 */
public class ProductDTO {

    private Integer ID;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    private String category;

    private List<SupplyDTO> distributors;

    public ProductDTO() {}

    public ProductDTO(Integer id, String name, String category, List<SupplyDTO> distributors) {
        this.ID = id;
        this.category = category;
        this.name = name;
        this.distributors = distributors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SupplyDTO> getDistributors() {
        return distributors;
    }

    public void setDistributors(List<SupplyDTO> distributors) {
        this.distributors = distributors;
    }
}
