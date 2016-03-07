package com.baranovskiy.webapp.model.entity;

import com.baranovskiy.webapp.model.BaseModel;
import com.baranovskiy.webapp.model.fields.Category;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Product extends BaseModel, represents product object
 * in logistic information system
 *
 * @version 1.0
 * @author Baranovskiy Artem
 */
@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(name = "Product.find.all", query = "from Product p"),
        @NamedQuery(name = "Product.find.by.name", query = "from Product p where p.name=:name")
})
public class Product extends BaseModel {

    /** @see com.baranovskiy.webapp.model.fields.Category */
    @Column(name = "category", nullable = false)
    private Category category;

    /**
     * Represents specific distributors, which are characterized by
     * quality and price
     */
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private List<Supply> distributors;

    public Product() {
        distributors = new ArrayList<>();
    }

    public Product(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @SuppressWarnings("unused")
    public void setCategory(Category category) {
        this.category = category;
    }

    @SuppressWarnings("unused")
    public List<Supply> getDistributors() {
        return new ArrayList<>(distributors);
    }

    @SuppressWarnings("unused")
    public void setDistributors(List<Supply> distributors) {
        this.distributors = distributors;
    }

    public void addDistributor(Supply supply) {
        distributors.add(supply);
    }

    @SuppressWarnings("unused")
    public void removeSupply(Supply supply) {
        distributors.remove(supply);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        Product product = (Product) o;
        return name.equals(product.name) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

}
