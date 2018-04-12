/*
 * Created by Zhen Guo on 2016.11.03  * 
 * Copyright © 2016 Zhen Guo. All rights reserved. * 
 */
package market.mymarket.managers;

import market.mymarket.entityclasses.Photo;
import market.mymarket.entityclasses.Customer;
import market.mymarket.sessionbeans.PhotoFacade;
import market.mymarket.sessionbeans.CustomerFacade;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

/*
---------------------------------------------------------------------------
JSF Managed Beans annotated with @ManagedBean from javax.faces.bean
is in the process of being deprecated in favor of CDI Beans. Therefore, 
you should use @Named from javax.inject package to designate a managed
bean as a JSF controller class. Contexts and Dependency Injection (CDI) 
beans annotated with @Named is the preferred approach, because CDI 
enables Java EE-wide dependency injection. CDI bean is a bean managed
by the CDI container. 

Within JSF XHTML pages, this bean will be referenced by using the name
accountManager. Actually, the default name is the class name starting
with a lower case letter and value = "accountManager" is optional;
However, we spell it out to make our code more readable and understandable.
---------------------------------------------------------------------------
 */
@Named(value = "accountManager")

/*
The @SessionScoped annotation preserves the values of the AccountManager
object's instance variables across multiple HTTP request-response cycles
as long as the user's established HTTP session is alive.
 */
@SessionScoped

/**
 *
 * @author Zhen
 */

/*
--------------------------------------------------------------------------
Marking the AccountManager class as "implements Serializable" implies that
instances of the class can be automatically serialized and deserialized. 

Serialization is the process of converting a class instance (object)
from memory into a suitable format for storage in a file or memory buffer, 
or for transmission across a network connection link.

Deserialization is the process of recreating a class instance (object)
in memory from the format under which it was stored.
--------------------------------------------------------------------------
 */
public class AccountManager implements Serializable {

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private String username;
    private String password;
    private String newPassword;

    private String firstName;
    private String middleName;
    private String lastName;

    private String ccNumber;
    private String ccExpires;
    private String ccSecurityCode;

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipcode;

    private int securityQuestion;
    private String securityAnswer;

    private String email;

    private final String[] listOfStates = Constants.STATES;
    private Map<String, Object> security_questions;
    private String statusMessage;

    private Customer selected;

    /*
    The @EJB annotation implies that the EJB container will perform an injection of the object
    reference of the CustomerFacade object into customerFacade when it is created at runtime.
     */
    @EJB
    private CustomerFacade customerFacade;

    /*
    The @EJB annotation implies that the EJB container will perform an injection of the object
    reference of the PhotoFacade object into photoFacade when it is created at runtime.
     */
    @EJB
    private PhotoFacade photoFacade;

    // Constructor method instantiating an instance of AccountManager
    public AccountManager() {
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public String[] getListOfStates() {
        return listOfStates;
    }

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String creditCardNumber) {
        this.ccNumber = creditCardNumber;
    }

    public String getCcExpires() {
        return ccExpires;
    }

    public void setCcExpires(String creditCardExpires) {
        this.ccExpires = creditCardExpires;
    }

    public String getCcSecurityCode() {
        return ccSecurityCode;
    }

    public void setCcSecurityCode(String creditCardSecurityCode) {
        this.ccSecurityCode = creditCardSecurityCode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zip_code) {
        this.zipcode = zip_code;
    }

    public int getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(int securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    private Map<String, Object> security_questions;
        String      int
        ---------   ---
        question1,  0
        question2,  1
        question3,  2
            :
    When the user selects a security question, its number (int) is stored; not its String.
    Later, given the number (int), the security question String is retrieved.
     */
    public Map<String, Object> getSecurity_questions() {

        if (security_questions == null) {
            /*
            Difference between HashMap and LinkedHashMap:
            HashMap stores key-value pairings in no particular order. Values are retrieved based on their corresponding Keys.
            LinkedHashMap stores and retrieves key-value pairings in the order they were put into the map.
             */
            security_questions = new LinkedHashMap<>();

            for (int i = 0; i < Constants.QUESTIONS.length; i++) {
                security_questions.put(Constants.QUESTIONS[i], i);
            }
        }
        return security_questions;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Customer getSelected() {

        if (selected == null) {
            /*
            customer_id (database primary key) was put into the SessionMap
            in the initializeSessionMap() method below or in LoginManager.
             */
            int customerPrimaryKey = (int) FacesContext.getCurrentInstance().
                    getExternalContext().getSessionMap().get("customer_id");
            /*
            Given the primary key, obtain the object reference of the Customer
            object and store it into the instance variable selected.
             */
            selected = customerFacade.find(customerPrimaryKey);
        }
        // Return the object reference of the selected Customer object
        return selected;
    }

    public void setSelected(Customer selectedCustomer) {
        this.selected = selectedCustomer;
    }

    // Return True if a customer is logged in; otherwise, return False
    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username") != null;
    }

    /*
    Create a new customer account. Return "" if an error occurs; otherwise,
    upon successful account creation, redirect to show the SignIn page.
     */
    public String createAccount() {

        // Obtain the object reference of the Customer object with username
        Customer aCustomer = customerFacade.findByUsername(username);

        if (aCustomer != null) {
            // A customer already exists with the username given
            username = "";
            statusMessage = "Username already exists! Please select a different one!";
            return "";
        }

        if (statusMessage == null || statusMessage.isEmpty()) {
            try {
                // Instantiate a new Customer object
                Customer customer = new Customer();

                // Dress up the newly created customer object with the values given
                customer.setFirstName(firstName);
                customer.setMiddleName(middleName);
                customer.setLastName(lastName);
                customer.setCcNumber(ccNumber);
                customer.setCcExpires(ccExpires);
                customer.setCcSecurityCode(ccSecurityCode);
                customer.setAddress1(address1);
                customer.setAddress2(address2);
                customer.setCity(city);
                customer.setState(state);
                customer.setZipcode(zipcode);
                customer.setSecurityQuestion(securityQuestion);
                customer.setSecurityAnswer(securityAnswer);
                customer.setEmail(email);
                customer.setUsername(username);
                customer.setPassword(password);
                customerFacade.create(customer);

            } catch (EJBException e) {
                username = "";
                statusMessage = "Something went wrong while creating your account!";
                return "";
            }
            // Initialize the session map for the newly created Customer object
            initializeSessionMap();

            /*
            The Profile page cannot be shown since the customer has not signed in yet.
            Therefore, we show the Sign In page for the customer to sign in first.
             */
            return "SignIn.xhtml?faces-redirect=true";
        }
        return "";
    }

    /*
    Update the logged-in customer's account profile. Return "" if an error occurs;
    otherwise, upon successful account update, redirect to show the Profile page.
     */
    public String updateAccount() {

        if (statusMessage == null || statusMessage.isEmpty()) {

            // Obtain the username of the logged-in customer
            String user_name = (String) FacesContext.getCurrentInstance().
                    getExternalContext().getSessionMap().get("username");

            // Obtain the object reference of the logged-in Customer object
            Customer editCustomer = customerFacade.findByUsername(user_name);

            try {
                // Set the logged-in Customer's properties to the given values
                editCustomer.setFirstName(this.selected.getFirstName());
                editCustomer.setMiddleName(this.selected.getMiddleName());
                editCustomer.setLastName(this.selected.getLastName());

                editCustomer.setCcNumber(this.selected.getCcNumber());
                editCustomer.setCcExpires(this.selected.getCcExpires());
                editCustomer.setCcSecurityCode(this.selected.getCcSecurityCode());

                editCustomer.setAddress1(this.selected.getAddress1());
                editCustomer.setAddress2(this.selected.getAddress2());
                editCustomer.setCity(this.selected.getCity());
                editCustomer.setState(this.selected.getState());
                editCustomer.setZipcode(this.selected.getZipcode());
                editCustomer.setEmail(this.selected.getEmail());

                // It is optional for the customer to change his/her password
                String new_Password = getNewPassword();

                if (new_Password == null || new_Password.isEmpty()) {
                    // Do nothing. The user does not want to change the password.
                } else {
                    editCustomer.setPassword(new_Password);
                    // Password changed successfully!
                    // Password was first validated by invoking the validatePasswordChange method below.
                }

                // The changes are stored in the database
                customerFacade.edit(editCustomer);

            } catch (EJBException e) {
                username = "";
                statusMessage = "Something went wrong while editing your profile!";
                return "";
            }
            // Account update is completed, redirect to show the Profile page.
            return "Profile.xhtml?faces-redirect=true";
        }
        return "";
    }

    /*
    Delete the logged-in customer's account. Return "" if an error occurs; otherwise,
    upon successful account deletion, redirect to show the index (home) page.
     */
    public String deleteAccount() {

        if (statusMessage == null || statusMessage.isEmpty()) {

            // Obtain the database primary key of the logged-in Customer object
            int customer_id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("customer_id");

            try {
                // Delete the photo files associated with the customer whose primary key is customer_id
                deletePhoto(customer_id);

                // Delete the Customer entity, whose primary key is customer_id, from the database
                customerFacade.deleteCustomer(customer_id);

            } catch (EJBException e) {
                username = "";
                statusMessage = "Something went wrong while deleting your account!";
                return "";
            }

            logout();
            return "index.xhtml?faces-redirect=true";
        }
        return "";
    }

    // Validate if the entered password matches the entered confirm password
    public void validateInformation(ComponentSystemEvent event) {

        /*
        FacesContext contains all of the per-request state information related to the processing of
        a single JavaServer Faces request, and the rendering of the corresponding response.
        It is passed to, and potentially modified by, each phase of the request processing lifecycle.
         */
        FacesContext fc = FacesContext.getCurrentInstance();

        /*
        UIComponent is the base class for all user interface components in JavaServer Faces. 
        The set of UIComponent instances associated with a particular request and response are organized into
        a component tree under a UIViewRoot that represents the entire content of the request or response.
         */
        // Obtain the UIComponent instances associated with the event
        UIComponent components = event.getComponent();

        /*
        UIInput is a kind of UIComponent for the user to enter a value in.
         */
        // Obtain the object reference of the UIInput field with id="password" on the UI
        UIInput uiInputPassword = (UIInput) components.findComponent("password");

        // Obtain the password entered in the UIInput field with id="password" on the UI
        String entered_password = uiInputPassword.getLocalValue()
                == null ? "" : uiInputPassword.getLocalValue().toString();

        // Obtain the object reference of the UIInput field with id="confirmPassword" on the UI
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");

        // Obtain the confirm password entered in the UIInput field with id="confirmPassword" on the UI
        String entered_confirm_password = uiInputConfirmPassword.getLocalValue()
                == null ? "" : uiInputConfirmPassword.getLocalValue().toString();

        if (entered_password.isEmpty() || entered_confirm_password.isEmpty()) {
            // Do not take any action. 
            // The required="true" in the XHTML file will catch this and produce an error message.
            return;
        }

        if (!entered_password.equals(entered_confirm_password)) {
            statusMessage = "Password and Confirm Password must match!";
        } else {
            statusMessage = "";
        }
    }

    // Validate the new password and new confirm password
    public void validatePasswordChange(ComponentSystemEvent event) {
        /*
        FacesContext contains all of the per-request state information related to the processing of
        a single JavaServer Faces request, and the rendering of the corresponding response.
        It is passed to, and potentially modified by, each phase of the request processing lifecycle.
         */
        FacesContext fc = FacesContext.getCurrentInstance();

        /*
        UIComponent is the base class for all user interface components in JavaServer Faces. 
        The set of UIComponent instances associated with a particular request and response are organized into
        a component tree under a UIViewRoot that represents the entire content of the request or response.
         */
        // Obtain the UIComponent instances associated with the event
        UIComponent components = event.getComponent();

        /*
        UIInput is a kind of UIComponent for the user to enter a value in.
         */
        // Obtain the object reference of the UIInput field with id="newPassword" on the UI
        UIInput uiInputPassword = (UIInput) components.findComponent("newPassword");

        // Obtain the new password entered in the UIInput field with id="newPassword" on the UI
        String new_Password = uiInputPassword.getLocalValue()
                == null ? "" : uiInputPassword.getLocalValue().toString();

        // Obtain the object reference of the UIInput field with id="newConfirmPassword" on the UI
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("newConfirmPassword");

        // Obtain the new confirm password entered in the UIInput field with id="newConfirmPassword" on the UI
        String new_ConfirmPassword = uiInputConfirmPassword.getLocalValue()
                == null ? "" : uiInputConfirmPassword.getLocalValue().toString();

        // It is optional for the customer to change his/her password
        if (new_Password.isEmpty() || new_ConfirmPassword.isEmpty()) {
            // Do nothing. The user does not want to change the password.
            return;
        }

        if (!new_Password.equals(new_ConfirmPassword)) {
            statusMessage = "New Password and New Confirm Password must match!";
        } else {
            /*
            REGular EXpression (regex) for validating password strength:
            (?=.{8,31})      ==> Validate the password to be minimum 8 and maximum 31 characters long. 
            (?=.*[!@#$%^&*]) ==> Validate the password to contain at least one special character. 
            (?=.*[A-Z])      ==> Validate the password to contain at least one uppercase letter. 
            (?=.*[a-z])      ==> Validate the password to contain at least one lowercase letter. 
            (?=.*[0-9])      ==> Validate the password to contain at least one number from 0 to 9.
             */
            String regex = "^(?=.{8,31})(?=.*[!@#$%^&*])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*$";

            if (!new_Password.matches(regex)) {
                statusMessage = "The password must be minimum 8 "
                        + "characters long, contain at least one special character, "
                        + "contain at least one uppercase letter, "
                        + "contain at least one lowercase letter, "
                        + "and contain at least one number 0 to 9.";
            } else {
                statusMessage = "";
            }
        }
    }

    // Validate if the entered password and confirm password are correct
    public void validateCustomerPassword(ComponentSystemEvent event) {
        /*
        FacesContext contains all of the per-request state information related to the processing of
        a single JavaServer Faces request, and the rendering of the corresponding response.
        It is passed to, and potentially modified by, each phase of the request processing lifecycle.
         */
        FacesContext fc = FacesContext.getCurrentInstance();

        /*
        UIComponent is the base class for all user interface components in JavaServer Faces. 
        The set of UIComponent instances associated with a particular request and response are organized into
        a component tree under a UIViewRoot that represents the entire content of the request or response.
         */
        // Obtain the UIComponent instances associated with the event
        UIComponent components = event.getComponent();

        /*
        UIInput is a kind of UIComponent for the user to enter a value in.
         */
        // Obtain the object reference of the UIInput field with id="password" on the UI
        UIInput uiInputPassword = (UIInput) components.findComponent("password");

        // Obtain the password entered in the UIInput field with id="password" on the UI
        String entered_password = uiInputPassword.getLocalValue()
                == null ? "" : uiInputPassword.getLocalValue().toString();

        // Obtain the object reference of the UIInput field with id="confirmPassword" on the UI
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");

        // Obtain the confirm password entered in the UIInput field with id="confirmPassword" on the UI
        String entered_confirm_password = uiInputConfirmPassword.getLocalValue()
                == null ? "" : uiInputConfirmPassword.getLocalValue().toString();

        if (entered_password.isEmpty() || entered_confirm_password.isEmpty()) {
            // Do not take any action. 
            // The required="true" in the XHTML file will catch this and produce an error message.
            return;
        }

        if (!entered_password.equals(entered_confirm_password)) {
            statusMessage = "Password and Confirm Password must match!";
        } else {
            // Obtain the logged-in Customer's username
            String user_name = (String) FacesContext.getCurrentInstance().
                    getExternalContext().getSessionMap().get("username");

            // Obtain the object reference of the logged-in Customer object
            Customer customer = customerFacade.findByUsername(user_name);

            if (entered_password.equals(customer.getPassword())) {
                // entered password = logged-in customer's password
                statusMessage = "";
            } else {
                statusMessage = "Incorrect Password!";
            }
        }
    }

    // Initialize the session map for the Customer object
    public void initializeSessionMap() {

        // Obtain the object reference of the Customer object
        Customer customer = customerFacade.findByUsername(getUsername());

        // Put the Customer's object reference into session map variable customer
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().put("customer", customer);

        // Put the Customer's database primary key into session map variable customer_id
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().put("customer_id", customer.getId());
    }

    /*
    UIComponent is the base class for all user interface components in JavaServer Faces. 
    The set of UIComponent instances associated with a particular request and response are organized into
    a component tree under a UIViewRoot that represents the entire content of the request or response.
     
    @param components: UIComponent instances associated with the current request and response
    @return True if entered password is correct; otherwise, return False
     */
    private boolean correctPasswordEntered(UIComponent components) {

        // Obtain the object reference of the UIInput field with id="verifyPassword" on the UI
        UIInput uiInputVerifyPassword = (UIInput) components.findComponent("verifyPassword");

        // Obtain the verify password entered in the UIInput field with id="verifyPassword" on the UI
        String verifyPassword = uiInputVerifyPassword.getLocalValue()
                == null ? "" : uiInputVerifyPassword.getLocalValue().toString();

        if (verifyPassword.isEmpty()) {
            statusMessage = "Please enter a password!";
            return false;

        } else if (verifyPassword.equals(password)) {
            // Correct password is entered
            return true;

        } else {
            statusMessage = "Invalid password entered!";
            return false;
        }
    }

    // Show the Home page
    public String showHomePage() {
        return "index?faces-redirect=true";
    }

    // Show the Profile page
    public String showProfile() {
        return "Profile?faces-redirect=true";
    }

    // Show the PrepareOrder page
    public String showPrepareOrder() {
        return "PrepareOrder?faces-redirect=true";
    }

    public String logout() {

        // Clear the logged-in Customer's session map
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

        // Reset the logged-in Customer's properties
        username = password = "";
        firstName = middleName = lastName = "";
        ccNumber = ccExpires = ccSecurityCode = "";
        address1 = address2 = city = state = zipcode = "";
        securityQuestion = 0;
        securityAnswer = "";
        email = statusMessage = "";

        // Invalidate the logged-in Customer's session
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        // Redirect to show the index (Home) page
        return "index.xhtml?faces-redirect=true";
    }

    public String customerPhoto() {

        // Obtain the username of the logged-in customer
        String user_name = (String) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("username");

        // Obtain the object reference of the logged-in Customer object
        Customer customer = customerFacade.findByUsername(user_name);

        /*
        Customer photo files are not stored in the database. Only the primary key (id) of the
        customer's photo is stored in the database.
        
        When customer uploads a photo, a thumbnail (small) version of the file is created
        in the saveThumbnail() method of FileManager by using the Scalr.resize method provided
        in the imgscalr (Java Image Scaling Library) imported as imgscalr-lib-4.2.jar

        Both uploaded and thumbnail photo files are named after the primary key (id) of the
        customer's photo and are stored in the PizzaHutStorageLocation. For example,
        for the primary key (id) = 25 and file extension = jpeg, the files are named as:
            e.g., 25.jpeg
            e.g., 25_thumbnail.jpeg
         */
        // Obtain a list of photo files (e.g., 25.jpeg and 25_thumbnail.jpeg) associated
        // with the logged-in Customer whose database primary key is customer.getId()
        List<Photo> photoList = photoFacade.findPhotosByCustomerID(customer.getId());

        if (photoList.isEmpty()) {
            // No customer photo exists. Return the default customer photo image.
            return "defaultCustomerPhoto.png";
        }

        /*
        photoList.get(0) returns the object reference of the first Photo object in the list.
        getThumbnailName() message is sent to that Photo object to retrieve its
        thumbnail image file name, e.g., 25_thumbnail.jpeg
         */
        return photoList.get(0).getThumbnailName();
    }

    // EL in Profile.xhtml invokes this method to obtain the constant value
    public String photoStorageDirectoryName() {
        return Constants.STORAGE_DIRECTORY;
    }

    /*
    Delete both uploaded and thumbnail photo files that belong to the Customer
    object whose database primary key is customerId
     */
    public void deletePhoto(int customerId) {

        /*
        Obtain the list of Photo objects that belong to the Customer whose
        database primary key is customerId.
        */
        List<Photo> photoList = photoFacade.findPhotosByCustomerID(customerId);

        if (!photoList.isEmpty()) {
            
            // Obtain the object reference of the first Photo object in the list.
            Photo photo = photoList.get(0);
            try {
                // Delete the uploaded photo file if it exists
                Files.deleteIfExists(Paths.get(photo.getFilePath()));

                // Delete the thumbnail image file if it exists
                Files.deleteIfExists(Paths.get(photo.getThumbnailFilePath()));

                // Delete the temporary file if it exists
                Files.deleteIfExists(Paths.get(Constants.ROOT_DIRECTORY + "tmp_file"));

                // Remove the customer photo's record from the database
                photoFacade.remove(photo);

            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
