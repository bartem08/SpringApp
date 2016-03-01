package com.baranovskiy.webapp.model.dto;

/**
 * Supply data transfer object. Uses for obtaining users data from
 * spring form tabs
 *
 * @see com.baranovskiy.webapp.model.BaseModel
 * @see com.baranovskiy.webapp.model.Supply
 * @version 1.0
 * @author Baranovskiy Artem
 */
public class SupplyDTO {

    private Integer ID;

    private String distributorName;

    private String productName;

    private String quality;

    private Float  price;

    public SupplyDTO() {}

    public SupplyDTO(Integer ID, String distributorName, String productName, String quality, Float price) {
        this.ID = ID;
        this.distributorName = distributorName;
        this.productName = productName;
        this.quality = quality;
        this.price = price;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}
