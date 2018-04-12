/*
 * Created by Zhen Guo on 2016.12.10  * 
 * Copyright © 2016 Zhen Guo. All rights reserved. * 
 */
package market.mymarket.entityclasses;

import market.mymarket.managers.Constants;  // Added
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zhen
 */
// The @Entity annotation designates this class as a JPA Entity POJO class representing the Photo table in the PizzaHutDB database.
@Entity

// Name of the PizzaHutDB database table represented
@Table(name = "photo")

@XmlRootElement
@NamedQueries({
    /* The following query is added */
    @NamedQuery(name = "Photo.findPhotosByCustomerID", query = "SELECT p FROM Photo p WHERE p.customerId.id = :customerId"),

    /* The following queries are auto generated */
    @NamedQuery(name = "Photo.findAll", query = "SELECT p FROM Photo p"),
    @NamedQuery(name = "Photo.findById", query = "SELECT p FROM Photo p WHERE p.id = :id"),
    @NamedQuery(name = "Photo.findByExtension", query = "SELECT p FROM Photo p WHERE p.extension = :extension")})

public class Photo implements Serializable {

    /*
    ========================================================
    Instance variables representing the attributes (columns)
    of the Photo table in the PizzaHutDB database.
    ========================================================
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "extension")
    private String extension;
    
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne
    private Customer customerId;

    /*
    ==============================================================
    Class constructors for instantiating a Photo entity object to
    represent a row in the Photo table in the PizzaHutDB database.
    ==============================================================
     */
    public Photo() {
    }

    public Photo(Integer id) {
        this.id = id;
    }

    public Photo(Integer id, String extension) {
        this.id = id;
        this.extension = extension;
    }
    
    // This method is added to the generated code
    public Photo(String extension, Customer id) {
        this.extension = extension;
        customerId = id;
    }

    /*
    ======================================================
    Getter and Setter methods for the attributes (columns)
    of the Photo table in the PizzaHutDB database.
    ======================================================
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    /**
     * @return Generates and returns a hash code value for the object with id
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Checks if the Photo object identified by 'object' is the same as the Photo object identified by 'id'
     *
     * @param object The Photo object identified by 'object'
     * @return True if the Photo 'object' and 'id' are the same; otherwise, return False
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Photo)) {
            return false;
        }
        Photo other = (Photo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * @return the String representation of a Photo id
     */
    @Override
    public String toString() {
        return "com.mycompany.entityclasses.Photo[ id=" + id + " ]";
    }

    /*
    =====================================================
    The following methods are added to the generated code
    =====================================================
     */
    public String getFilePath() {
        return Constants.ROOT_DIRECTORY + getFilename();
    }

    // Customer's photo image file is named as customer's unique id.file extension name.
    // Example: 38.jpeg
    public String getFilename() {
        return getId() + "." + getExtension();
    }

    public String getThumbnailFilePath() {
        return Constants.ROOT_DIRECTORY + getThumbnailName();
    }

    /*
    Customer's photo thumbnail image file is named as 
    the primary key (id) of the customer's photo_thumbnail.file extension name.
    Example: 38_thumbnail.jpeg
    */
    public String getThumbnailName() {
        return getId() + "_thumbnail." + getExtension();
    }

}
