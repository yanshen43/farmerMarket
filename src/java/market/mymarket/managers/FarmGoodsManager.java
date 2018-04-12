/*
 * Created by Zhen Guo on 2016.12.13  * 
 * Copyright © 2016 Zhen Guo. All rights reserved. * 
 */
package market.mymarket.managers;

import java.io.IOException;
import market.mymarket.entityclasses.Goodslist;
import market.mymarket.entityclasses.Farm;
import market.mymarket.sessionbeans.GoodslistFacade;
import market.mymarket.sessionbeans.FarmFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ZG
 */
@Named("farmgoodsManager")
@SessionScoped

public class FarmGoodsManager implements Serializable{
    
    /*
    -------------------------------
    Instance Variables (Properties)
    -------------------------------
     */
    /*
    ejbFacade is a reference (pointer) to an object that belongs to the GoodslistFacade class. 
    It is annotated with the @EJB annotation implying that the GlassFish application server, at runtime, 
    will inject in this instance variable, a reference to the @Stateless session bean ejbFacade.
     */    
    @EJB
    private GoodslistFacade goodslistFacade;
    @EJB
    private FarmFacade farmFacade;
    
    // 'items' is an array containing the object references of Farm
    private List<Farm> items = null;
    // 'selected' contains the object reference of the selected Farm
    private Farm selected;    
    // 'searchItems' is an array containing the object references of Goodslists found in the search
    private List<Goodslist> searchItems = null;       
   
    /*
    -----------------------------------------------------
    This is the constructor method invoked to instantiate
    an object from the CompanyController class
    -----------------------------------------------------
     */
    public FarmGoodsManager() {
    }
    /*
    -------------------------
    Getter and Setter Methods
    -------------------------
     */
    public Farm getSelected() {
        return selected;
    }
    
    public void setSelected(Farm selected) {
        this.selected = selected;
    }
    
    private FarmFacade getFarmFacade() {
        return farmFacade;
    }
    
    private GoodslistFacade getFacade() {
        return goodslistFacade;
    }
    
    /*
    -----------------------
    Embeddable Primary Keys
    -----------------------
     */    
    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }

    /*
    Return the list of object references of all those companies where 
    the search string 'searchString' entered by the user is contained in the searchField.
     */
    public List<Goodslist> getSearchItems() {
        // Return the list of object references of all those Goodslists where 
        // Farmer's Market ID equals to FarmController.goodsselected.fmid
        return getFacade().fmidQuery(selected.getFmid());
    }  
 
    /*
    If 'items' list is empty, obtain the object references of all of the companies
    in the database, store them in the 'items" array (list), and return them.
     */    
    public List<Farm> getItems() {
        if (items == null) {
            // FarmFacade inherits the findAll() method from the AbstractFacade class
            items = getFarmFacade().findAll();
        }
        return items;
    }
  
    /**
     * Returns the object reference of the Farm whose primary key = id
     *
     * @param id Company's id (primary key)
     * @return the object reference of the Company found
     */
    public Farm getFarm(java.lang.String id) {
        // CompanyFacade inherits the find(id) method from the AbstractFacade class
        return getFarmFacade().find(id);
    }

    /**
     * Returns the object reference of the Goodslist whose primary key = id
     *
     * @param id GLID (primary key)
     * @return the object reference of the Goodslist found
     */
    public Goodslist getGoodslist(java.lang.Integer id) {
        // GoodslistFacade inherits the find(id) method from the AbstractFacade class
        return getFacade().find(id);
    }
}
