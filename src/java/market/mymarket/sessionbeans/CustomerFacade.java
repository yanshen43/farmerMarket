/*
 * Created by Zhen Guo on 2016.12.10  * 
 * Copyright © 2016 Zhen Guo. All rights reserved. * 
 */
package market.mymarket.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import market.mymarket.entityclasses.Customer;

/**
 *
 * @author Zhen
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {
    /*
    ----------------------------------------------------------------------------------------------------
    Annotating 'private EntityManager em;' with '@PersistenceContext(unitName = "PizzaHut-BalciPU")' 
    implies that the EntityManager instance pointed to by 'em' is associated with the 'PizzaHut-BalciPU'
    persistence context. The persistence context is a set of entity (Customer) instances in which for
    any persistent entity (Customer) identity, there is a unique entity (Customer) instance.
    Within the persistence context, the entity (Customer) instances and their life cycle are managed.
    The EntityManager API is used to create and remove persistent entity (Customer) instances,
    to find entities by their primary key, and to query over entities (Customers).
    ----------------------------------------------------------------------------------------------------
     */
    @PersistenceContext(unitName = "FarmersMarketPU")
    private EntityManager em;

    // @Override annotation indicates that the super class AbstractFacade's getEntityManager() method is overridden.
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /* 
    This constructor method invokes the parent abstract class AbstractFacade.java's
    constructor method AbstractFacade, which in turn initializes its entityClass instance
    variable with the Customer class object reference returned by the Customer.class.
     */
    public CustomerFacade() {
        super(Customer.class);
    }
    /*
    -----------------------------------------------------
    The following methods are added to the generated code
    -----------------------------------------------------
     */
    /**
     * @param id is the Primary Key of the Customer entity in a table row in the PizzaHutDB database.
     * @return object reference of the Customer entity whose primary key is id
     */
    public Customer getCustomer(int id) {
        
        // The find method is inherited from the parent AbstractFacade class
        return em.find(Customer.class, id);
    }

    /**
     * @param username is the username attribute (column) value of the customer
     * @return object reference of the Customer entity whose username is username
     */
    public Customer findByUsername(String username) {
        if (em.createQuery("SELECT c FROM Customer c WHERE c.username = :username")
                .setParameter("username", username)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (Customer) (em.createQuery("SELECT c FROM Customer c WHERE c.username = :username")
                    .setParameter("username", username)
                    .getSingleResult());
        }
    }

    /**
     * Deletes the Customer entity whose primary key is id
     * @param id is the Primary Key of the Customer entity in a table row in the PizzaHutDB database.
     */
    public void deleteCustomer(int id) {
        
        // The find method is inherited from the parent AbstractFacade class
        Customer customer = em.find(Customer.class, id);
        
        // The remove method is inherited from the parent AbstractFacade class
        em.remove(customer); 
    }
    
}
