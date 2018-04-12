/*
 * Created by Zhen Guo on 2016.11.03  * 
 * Copyright © 2016 Zhen Guo. All rights reserved. * 
 */
package market.mymarket.managers;

import market.mymarket.entityclasses.Customer;
import market.mymarket.sessionbeans.CustomerFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "loginManager")
@SessionScoped
/**
 *
 * @author Zhen
 */
public class LoginManager implements Serializable {

    // Instance Variables (Properties)
    private String username;
    private String password;
    private String errorMessage;

    /*
    The @EJB annotation implies that the EJB container will perform an injection of the object
    reference of the CustomerFacade object into customerFacade when it is created at runtime.
     */
    @EJB
    private CustomerFacade customerFacade;

    // Constructor method instantiating an instance of LoginManager
    public LoginManager() {
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /*
    ================
    Instance Methods
    ================
     */
    public String createCustomer() {

        // Redirect to show the CreateAccount page
        return "CreateAccount.xhtml?faces-redirect=true";
    }

    public String resetPassword() {

        // Redirect to show the EnterUsername page
        return "EnterUsername.xhtml?faces-redirect=true";
    }

    /*
    Sign in the customer if the entered username and password are valid
    @return "" if an error occurs; otherwise, redirect to show the Profile page
     */
    public String loginCustomer() {

        // Obtain the object reference of the Customer object from the entered username
        Customer customer = customerFacade.findByUsername(getUsername());

        if (customer == null) {
            errorMessage = "Entered username " + getUsername() + " does not exist!";
            return "";

        } else {
            String actualUsername = customer.getUsername();
            String enteredUsername = getUsername();

            String actualPassword = customer.getPassword();
            String enteredPassword = getPassword();

            if (!actualUsername.equals(enteredUsername)) {
                errorMessage = "Invalid Username!";
                return "";
            }

            if (!actualPassword.equals(enteredPassword)) {
                errorMessage = "Invalid Password!";
                return "";
            }

            errorMessage = "";

            // Initialize the session map with customer properties of interest
            initializeSessionMap(customer);

            // Redirect to show the Profile page
            return "Profile.xhtml?faces-redirect=true";
        }
    }

    /*
    Initialize the session map with the customer properties of interest,
    namely, first_name, last_name, username, and customer_id.
    customer_id = primary key of the customer entity in the database
     */
    public void initializeSessionMap(Customer customer) {
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().put("first_name", customer.getFirstName());
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().put("last_name", customer.getLastName());
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().put("username", username);
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().put("customer_id", customer.getId());
    }

}

