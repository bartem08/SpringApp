package com.baranovskiy.webapp.model;

import com.baranovskiy.webapp.model.fields.Quality;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;

/**
 * Linked entity, which wires distributor and product. Has
 * specified fields as quality and price, which defines
 * unique supply.
 *
 * @version 1.0
 * @author Baranovskiy Artem
 */
@Entity
@Table(name = "supply")
@NamedQueries({
        @NamedQuery(name = "Supply.find.all", query = "from Supply s"),
        @NamedQuery(name = "Supply.find.by.name", query = "from Supply s where s.name=:name")
})
public class Supply extends BaseModel {

    @JoinColumn(name = "distributor_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Distributor distributor;

    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Product product;

    @Column(name = "quality", nullable = false)
    private Quality quality;

    @Column(name = "price", nullable = false)
    private Float price;

    public Supply() {}

    public Supply(Distributor distributor, Product product, Quality quality, Float price) {
        this.distributor = distributor;
        this.product = product;
        this.quality = quality;
        this.price = price;
        this.name = String.format("%d-%d-%d-%.2f", distributor.getID(), product.getID(), quality.getValue(), price);
        this.product.addDistributor(this);
        this.distributor.addProduct(this);
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Quality getQuality() {
        return quality;
    }

    @SuppressWarnings("unused")
    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public Float getPrice() {
        return price;
    }

    @SuppressWarnings("unused")
    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        Supply that = (Supply) o;
        return distributor.equals(that.distributor) && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int result = distributor.hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s-%s-%s-%.2f",
                distributor.getName(), product.getName(), getQuality(), price);
    }

}
