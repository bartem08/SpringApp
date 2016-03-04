package com.baranovskiy.webapp.model.dto;

import com.baranovskiy.webapp.util.DateConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.List;

/**
 * Distributor data transfer object.
 *
 * @see com.baranovskiy.webapp.model.BaseModel
 * @see com.baranovskiy.webapp.model.Distributor
 * @version 1.0
 * @author Baranovskiy Artem
 */
public class DistributorDTO {

    private Integer ID;

    @NotNull
    @Size(min = 3, max = 10)
    private String name;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String date;

    private List<SupplyDTO> products;

    public DistributorDTO() {
        this.date = DateConverter.calendarToString(Calendar.getInstance());
    }

    public DistributorDTO(Integer id, String name, Calendar date, List<SupplyDTO> products) {
        this.ID = id;
        this.name = name;
        this.date = DateConverter.calendarToString(date);
        this.products = products;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<SupplyDTO> getProducts() {
        return products;
    }

    public void setProducts(List<SupplyDTO> products) {
        this.products = products;
    }
}
