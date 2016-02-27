package com.baranovskiy.webapp.repository;

import com.baranovskiy.webapp.model.Distributor;
import org.springframework.stereotype.Repository;

/**
 * Class extends AbstractJdbcDAO and is used for
 * distributors manipulation in the repository
 *
 */
@Repository
public class HbnDistributorDAO extends AbstractHibernateDAO<Distributor> {

    @Override
    public Class getEntityClass() {
        return Distributor.class;
    }

    @Override
    public String getEntityName() {
        return "Distributor";
    }

    @Override
    public String getUpdateQuery(Distributor object) {
        return String.format("update Distributor set name='%s'", object.getName());
    }

}
