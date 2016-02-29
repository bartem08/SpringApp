package com.baranovskiy.webapp.model.dto;

import com.baranovskiy.webapp.util.DateConverter;

import java.util.Calendar;

/**
 * Distributor data transfer object.
 *
 * @see com.baranovskiy.webapp.model.BaseModel
 * @see com.baranovskiy.webapp.model.Distributor
 * @version 1.0
 * @author Baranovskiy Artem
 */

public class DistributorDTO {

    private Integer id;
    private String name;
    private String date;

    public DistributorDTO() {}

    public DistributorDTO(Integer id, String name, Calendar date) {
        this.id = id;
        this.name = name;
        this.date = DateConverter.calendarToString(date);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
