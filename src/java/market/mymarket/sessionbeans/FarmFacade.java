/*
 * Created by Zhen Guo on 2016.12.10  * 
 * Copyright © 2016 Zhen Guo. All rights reserved. * 
 */
package market.mymarket.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import market.mymarket.entityclasses.Farm;

/**
 *
 * @author ZG
 */
@Stateless
public class FarmFacade extends AbstractFacade<Farm> {

    @PersistenceContext(unitName = "FarmersMarketPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FarmFacade() {
        super(Farm.class);
    }
    
}
