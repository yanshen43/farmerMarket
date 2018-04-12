/*
 * Created by Zhen Guo on 2016.12.10  * 
 * Copyright © 2016 Zhen Guo. All rights reserved. * 
 */
package market.mymarket.sessionbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import market.mymarket.entityclasses.Goodslist;

/**
 *
 * @author ZG
 */
// @Stateless annotation implies that the conversational state with the client shall NOT be maintained.
@Stateless
public class GoodslistFacade extends AbstractFacade<Goodslist> {

    /*
    Annotating 'private EntityManager em;' with '@PersistenceContext(unitName = "FarmersMarketPU")' implies that
    the EntityManager instance pointed to by 'em' is associated with the 'FarmersMarketPU' persistence context. 
    The persistence context is a set of entity (Goodslist) instances in which for any persistent
    entity (Goodslist) identity, there is a unique entity (Goodslist) instance. 
    Within the persistence context, the entity (Goodslist) instances and their life cycle are managed. 
    The EntityManager API is used to create and remove persistent entity (Goodslist) instances, 
    to find entities by their primary key, and to query over entities (Goodslist).
     */
    @PersistenceContext(unitName = "FarmersMarketPU")
    private EntityManager em;   // 'em' holds the object reference to the instantiated EntityManager object.

    // @Override annotation indicates that the super class AbstractFacade's getEntityManager() method is overridden.
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /* This constructor method invokes the parent abstract class AbstractFacade.java's 
     * constructor method, which in turn initializes its entityClass instance variable
     * with the Goodslist class object reference returned by the Goodslist.class. 
     */
    public GoodslistFacade() {
        super(Goodslist.class); // Invokes super's AbstractFacade constructor method by passing
        // Goodslist.class, which is the object reference of the Goodslist class.
    }
    
    /*
    See http://docs.oracle.com/javaee/7/api/ for documentation of Java EE 7 API packages, classes, and methods.
    
    Read https://docs.oracle.com/javaee/6/tutorial/doc/bnbtl.html#bnbtm to learn about Simple Queries,
    specifically the ones with the LIKE expression.
    
    The LIKE Expression
        SELECT p FROM Player p WHERE p.name LIKE ’Mich%’
    
        Data retrieved: All players whose names begin with “Mich”
    
        Description: The LIKE expression uses wildcard characters to search for strings that match the wildcard pattern.
        In this case, the query uses the LIKE expression and the % wildcard to find all players whose names begin with
        the string “Mich” For example, “Michael” and “Michelle” both match the wildcard pattern.

    ================================
    EntityManager Method createQuery
    ================================
    Query createQuery(String qlString)
        Create an instance of Query for executing a Java Persistence query language statement.
    Parameter:
        qlString - a Java Persistence query string, e.g., "SELECT c FROM Company c WHERE c.companyName LIKE :searchString"
    Returns:
        the object reference of the newly created Query object

    =========================
    Query Method setParameter
    =========================
    Query setParameter(String name, Object value)
        Bind an argument value to a named parameter.
    Parameters:
        name - parameter name (In our case, "searchString")
        value - parameter value (In our case, the searchString parameter that contains the search string the user entered for searching a name or names)
    Returns:
        the same object reference of the newly created Query object

    ==========================
    Query Method getResultList
    ==========================
    List getResultList()
        Execute a SELECT query and return the query results as an untyped List.
    Returns:
        the object reference of the newly created List containing the search results

    ---------------------------------
    Search Category: Goodslist FMID
    ---------------------------------
     */
    /**
     * Searches Goodslist for rows where FMID equals to the searchID from FarmController.selected.fmid .
     *
     * @param searchID contains the search Integer for FMID
     * @return A list of Company object references as the search results
     */
    public List<Goodslist> fmidQuery(String searchID) {
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery("SELECT c FROM Goodslist c WHERE c.fmid = :searchID").setParameter("searchID", searchID).getResultList();
    }
}
