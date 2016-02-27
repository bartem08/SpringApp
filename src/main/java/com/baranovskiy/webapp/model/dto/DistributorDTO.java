package com.baranovskiy.webapp.model.dto;

import java.util.Calendar;

/**
 * Distributor data transfer object.
 *
 * @see com.baranovskiy.webapp.model.BaseModel
 * @see com.baranovskiy.webapp.model.Distributor
 * @version 1.0
 * @author Baranovskiy Artem
 */
@SuppressWarnings("unused")
public class DistributorDTO {

    private Integer id;
    private String name;
    private Calendar date;

    public DistributorDTO() {}

    public DistributorDTO(Integer id, String name, Calendar date) {
        this.id = id;
        this.name = name;
        this.date = date;
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

}
