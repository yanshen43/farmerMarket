/*
 * Created by Zhen Guo on 2016.12.10  * 
 * Copyright © 2016 Zhen Guo. All rights reserved. * 
 */
package market.mymarket.sessionbeans;

import java.util.List;
/*
------------------------------------------------------------------------------
- An instance of javax.persistence.EntityManager represents an Entity Manager.
- An Entity Manager manages JPA Entities.
- Each Entity Manager instance is associated with a persistence context.
- A persistence context is a set of managed entity instances.
------------------------------------------------------------------------------
 */
import javax.persistence.EntityManager;

/**
 *
 * @author Zhen
 * @param <T> refers to the Class Type
 */
public abstract class AbstractFacade<T> {

    // An instance variable pointing to a class object of type T
    private final Class<T> entityClass;

    /* 
    ---------------------------------------------------------------------------------
    This is the constructor method called by the subclass GoodslistFacade.java class's
    constructor method by passing the Goodslist.class as a parameter.
    Goodslist.class returns an object reference to the Goodslist class, which is set
    as the value of the entityClass instance variable.
    The class type T is set to the Goodslist entity class. So, T = Goodslist
    ---------------------------------------------------------------------------------
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /* 
    This method is overridden in GoodslistFacade.java, which is the
    concrete Facade subclass providing the actual implementation.
     */
    protected abstract EntityManager getEntityManager();

    /**
     * Stores the newly CREATED Goodslist (entity) object into the database
     *
     * @param entity contains object reference of the Customer
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * Stores the EDITED Goodslist (entity) object into the database
     *
     * @param entity contains object reference of the Goodslist
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Deletes the given Goodslist (entity) object from the database
     *
     * @param entity contains object reference of the Goodslist
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Finds and returns a Customer in the database by using its Primary Key (id)
     *
     * @param id is the Primary Key of the Customer
     * @return object reference of the Customer found
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * @return a list of object references of all of the customers in the database
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /*
    ---------------------------------------------------------------------------------
    Finds and returns a list of Goodslist within a certain id range.
    
    Returns a List of Goodslist objects in a range from the database. The range is
    specified by the range parameter of type integer array. The 1st element of the
    range array = index number of the first Goodslist object to retrieve. The 2nd
    element of the range array = index number of the last Goodslist object to retrieve.
    ---------------------------------------------------------------------------------
     */
    
    /**
     * @param range The id range
     * @return a list of goodslist within a certain id range
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * Obtains and returns the total number of goodslist in the database
     * @return the total number of goodslist
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
