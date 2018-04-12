/*
 * Created by Yanshen Yang on 2016.12.14  * 
 * Copyright © 2016 Yanshen Yang. All rights reserved. * 
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
@Table(name = "goods")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Goods.findAll", query = "SELECT g FROM Goods g"),
    @NamedQuery(name = "Goods.findByGid", query = "SELECT g FROM Goods g WHERE g.gid = :gid"),
    @NamedQuery(name = "Goods.findByGName", query = "SELECT g FROM Goods g WHERE g.gName = :gName"),
    @NamedQuery(name = "Goods.findByImage", query = "SELECT g FROM Goods g WHERE g.image = :image"),
    @NamedQuery(name = "Goods.findByPrice", query = "SELECT g FROM Goods g WHERE g.price = :price"),
    @NamedQuery(name = "Goods.findByUnit", query = "SELECT g FROM Goods g WHERE g.unit = :unit"),
    @NamedQuery(name = "Goods.findByCategory", query = "SELECT g FROM Goods g WHERE g.category = :category")})
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GID")
    private Integer gid;
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

    public Goods() {
    }

    public Goods(Integer gid) {
        this.gid = gid;
    }

    public Goods(Integer gid, String gName, String image, BigDecimal price, String unit, String category) {
        this.gid = gid;
        this.gName = gName;
        this.image = image;
        this.price = price;
        this.unit = unit;
        this.category = category;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gid != null ? gid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Goods)) {
            return false;
        }
        Goods other = (Goods) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "market.mymarket.entityclasses.Goods[ gid=" + gid + " ]";
    }
    
}
