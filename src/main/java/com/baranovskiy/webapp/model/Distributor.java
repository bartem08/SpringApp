package com.baranovskiy.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Distributor extends BaseModel, represents distributor object
 * in logistic information system
 *
 * @version 1.0
 * @author Baranovskiy Artem
 *
 */
@Entity
@Table(name = "distributor")
@NamedQueries({
        @NamedQuery(name = "Distributor.find.all", query = "from Distributor d"),
        @NamedQuery(name = "Distributor.find.by.name", query = "from Distributor d where d.name=:name")
})
public class Distributor extends BaseModel {

    @Column(name = "cooperatedDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar cooperatedDate;

    /**
     * Represents specific products, which are characterized by
     * quality and price
     */
    @OneToMany(mappedBy = "distributor", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private List<Supply> products;

    public Distributor() {
        products = new ArrayList<>();
        cooperatedDate = Calendar.getInstance();
    }

    public Distributor(String name) {
        this.name = name;
        this.cooperatedDate = Calendar.getInstance();
        this.products = new ArrayList<>();
    }

    public Calendar getCooperatedDate() {
        return cooperatedDate;
    }

    public void setCooperatedDate(Calendar cooperatedDate) {
        this.cooperatedDate = cooperatedDate;
    }

    public List<Supply> getProducts() {
        return new ArrayList<>(products);
    }

    public void setProducts(List<Supply> products) {
        this.products = products;
    }

    public void addProduct(Supply supply) {
        products.add(supply);
    }

    public void removeProduct(Supply supply) {
        products.remove(supply);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Distributor other = (Distributor) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

}
