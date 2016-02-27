package com.baranovskiy.webapp.repository;

import com.baranovskiy.webapp.model.Product;
import org.springframework.stereotype.Repository;

/**
 * Class extends AbstractJdbcDAO and is used for
 * products manipulation in the repository
 *
 */
@Repository
public class HbnProductDAO extends AbstractHibernateDAO<Product> {

    @Override
    public Class getEntityClass() {
        return Product.class;
    }

    @Override
    public String getEntityName() {
        return "Product";
    }

    @Override
    public String getUpdateQuery(Product object) {
        return String.format("update Product set name='%s', category=%d",
                object.getName(), object.getCategory().getValue());
    }

}
