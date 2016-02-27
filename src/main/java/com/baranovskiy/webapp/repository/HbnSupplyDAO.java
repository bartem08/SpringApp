package com.baranovskiy.webapp.repository;

import com.baranovskiy.webapp.model.Supply;
import org.springframework.stereotype.Repository;

/**
 * Class extends AbstractJdbcDAO and is used for
 * supplies manipulation in the repository
 *
 */
@Repository
public class HbnSupplyDAO extends AbstractHibernateDAO<Supply> {

    @Override
    public Class getEntityClass() {
        return Supply.class;
    }

    @Override
    public String getEntityName() {
        return "Supply";
    }

    @Override
    public String getUpdateQuery(Supply object) {
        return String.format("update Supply set name='%s', distributor_id=%d," +
                        " product_id=%d, quality=%d, price=%.0f",
                object.getName(), object.getDistributor().getID(), object.getProduct().getID(),
                object.getQuality().getValue(), object.getPrice());
    }

}
