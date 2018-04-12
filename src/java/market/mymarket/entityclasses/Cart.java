/*
 * Created by Ke Tian on 2016.12.14  * 
 * Copyright © 2016 Ke Tian. All rights reserved. * 
 */
package market.mymarket.entityclasses;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ketian
 */
@Entity
@Table(name = "cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
    @NamedQuery(name = "Cart.findByCartID", query = "SELECT c FROM Cart c WHERE c.cartID = :cartID"),
    @NamedQuery(name = "Cart.findByGlid", query = "SELECT c FROM Cart c WHERE c.glid = :glid"),
    @NamedQuery(name = "Cart.findByGName", query = "SELECT c FROM Cart c WHERE c.gName = :gName"),
    @NamedQuery(name = "Cart.findByImage", query = "SELECT c FROM Cart c WHERE c.image = :image"),
    @NamedQuery(name = "Cart.findByPrice", query = "SELECT c FROM Cart c WHERE c.price = :price"),
    @NamedQuery(name = "Cart.findByUnit", query = "SELECT c FROM Cart c WHERE c.unit = :unit"),
    @NamedQuery(name = "Cart.findByCategory", query = "SELECT c FROM Cart c WHERE c.category = :category"),
    @NamedQuery(name = "Cart.findByQuantity", query = "SELECT c FROM Cart c WHERE c.quantity = :quantity")})
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CartID")
    private Integer cartID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GLID")
    private int glid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "GName")
    private String gName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "Image")
    private String image;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "Unit")
    private String unit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "Category")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity")
    private int quantity;

    public Cart() {
    }

    public Cart(Integer cartID) {
        this.cartID = cartID;
    }

    public Cart(Integer cartID, int glid, String gName, String image, BigDecimal price, String unit, String category, int quantity) {
        this.cartID = cartID;
        this.glid = glid;
        this.gName = gName;
        this.image = image;
        this.price = price;
        this.unit = unit;
        this.category = category;
        this.quantity = quantity;
    }

    public Integer getCartID() {
        return cartID;
    }

    public void setCartID(Integer cartID) {
        this.cartID = cartID;
    }

    public int getGlid() {
        return glid;
    }

    public void setGlid(int glid) {
        this.glid = glid;
    }

    public String getGName() {
        return gName;
    }

    public void setGName(String gName) {
        this.gName = gName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartID != null ? cartID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.cartID == null && other.cartID != null) || (this.cartID != null && !this.cartID.equals(other.cartID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "market.mymarket.entityclasses.Cart[ cartID=" + cartID + " ]";
    }
    
}
