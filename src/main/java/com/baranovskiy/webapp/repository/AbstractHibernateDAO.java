package com.baranovskiy.webapp.repository;

import com.baranovskiy.webapp.model.BaseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Abstract class, which provides basic realisation of C.R.U.D. methods
 * using Hibernate framework
 *
 * @version 1.0
 * @author Baranovskiy Artem
 * @param <T> Object type of persistence
 */
public abstract class AbstractHibernateDAO<T extends BaseModel> implements Operable<T> {

    private static final Logger LOG = LogManager.getLogger(AbstractHibernateDAO.class);

    /** Defined in data.xml **/
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Returns entity class (extends BaseModel)
     *
     * @return [Distributor.class, Product.class, Supply.class]
     */
    public abstract Class getEntityClass();

    /**
     * Returns entity name with the specified alias
     *
     * @return [Distributor distributor, Product product, Supply supply]
     */
    public abstract String getEntityName();

    /**
     * Returns hql statement for updating record with specified id.
     *
     * @return UPDATE [TABLE_NAME] SET [column1=?, ...] WHERE id=?;
     */
    public abstract String getUpdateQuery(T object);

    @Transactional
    @Override
    public void add(T object) {
        Session session = sessionFactory.getCurrentSession();
        LOG.info(String.format("attempt to add %s", object.getName()));
        session.save(object);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public void update(T object) {
        Session session = sessionFactory.getCurrentSession();
        LOG.info(String.format("attempt to update %s", object.getName()));
        Query query = session.createQuery(String.format("%s where id=:id", getUpdateQuery(object)));
        query.setParameter("id", object.getID());
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        T object = (T)session.get(getEntityClass(), id);
        LOG.info(String.format("attempt to delete %s", object.getName()));
        session.delete(object);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public T findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        LOG.info(String.format("attempt to find %s", name));
        Query query = session.getNamedQuery(String.format("%s.find.by.name", getEntityName()));
        query.setParameter("name", name);
        return (T)query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public T findByID(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return (T)session.get(getEntityClass(), id);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery(String.format("%s.find.all", getEntityName())).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<T> getAllSortedByName() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(String.format("from %s order by name", getEntityName())).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<T> getAllSortedById() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(getEntityClass());
        criteria.addOrder(Order.asc("ID"));
        return criteria.list();
    }

}
