/*
 * Created by Ke Tian on 2016.12.14  * 
 * Copyright © 2016 Ke Tian. All rights reserved. * 
 */
package market.mymarket.entityclasses;

import java.io.Serializable;
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
@Table(name = "category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByCid", query = "SELECT c FROM Category c WHERE c.cid = :cid"),
    @NamedQuery(name = "Category.findByFmid", query = "SELECT c FROM Category c WHERE c.fmid = :fmid"),
    @NamedQuery(name = "Category.findByCName", query = "SELECT c FROM Category c WHERE c.cName = :cName"),
    @NamedQuery(name = "Category.findByIcon", query = "SELECT c FROM Category c WHERE c.icon = :icon")})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CID")
    private Integer cid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "FMID")
    private String fmid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "CName")
    private String cName;
    @Size(max = 32)
    @Column(name = "Icon")
    private String icon;

    public Category() {
    }

    public Category(Integer cid) {
        this.cid = cid;
    }

    public Category(Integer cid, String fmid, String cName) {
        this.cid = cid;
        this.fmid = fmid;
        this.cName = cName;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getFmid() {
        return fmid;
    }

    public void setFmid(String fmid) {
        this.fmid = fmid;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "market.mymarket.entityclasses.Category[ cid=" + cid + " ]";
    }
    
}
