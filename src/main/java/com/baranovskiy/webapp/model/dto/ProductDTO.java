package com.baranovskiy.webapp.model.dto;

/**
 * Product data transfer object.
 *
 * @see com.baranovskiy.webapp.model.BaseModel
 * @see com.baranovskiy.webapp.model.Distributor
 * @version 1.0
 * @author Baranovskiy Artem
 */
@SuppressWarnings("unused")
public class ProductDTO {

    private Integer id;
    private String name;
    private String category;

    public ProductDTO() {}

    public ProductDTO(Integer id, String category, String name) {
        this.id = id;
        this.category = category;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
