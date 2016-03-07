package com.baranovskiy.webapp.model.dto;

import com.baranovskiy.webapp.validator.annotation.FloatValue;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Supply data transfer object. Uses for obtaining users data from
 * spring form tabs
 *
 * @see com.baranovskiy.webapp.model.BaseModel
 * @see com.baranovskiy.webapp.model.Supply
 * @version 1.0
 * @author Baranovskiy Artem
 */
public class SupplyDTO extends BaseDTO {

    @NotNull
    private String distributorName;

    @NotNull
    @Size(min = 1, max = 22)
    private String productName;

    @NotNull
    private String quality;

    @NotNull
    @FloatValue(min = 0.01f, max = 9999.99f)
    private String price;

    public SupplyDTO() {}

    public SupplyDTO(Integer ID, String distributorName, String productName, String quality, String price) {
        this.ID = ID;
        this.distributorName = distributorName;
        this.productName = productName;
        this.quality = quality;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
