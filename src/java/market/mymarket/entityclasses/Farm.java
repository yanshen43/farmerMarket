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
@Table(name = "farm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Farm.findAll", query = "SELECT f FROM Farm f"),
    @NamedQuery(name = "Farm.findByFmid", query = "SELECT f FROM Farm f WHERE f.fmid = :fmid"),
    @NamedQuery(name = "Farm.findByMarketName", query = "SELECT f FROM Farm f WHERE f.marketName = :marketName"),
    @NamedQuery(name = "Farm.findByWebsite", query = "SELECT f FROM Farm f WHERE f.website = :website"),
    @NamedQuery(name = "Farm.findByFacebook", query = "SELECT f FROM Farm f WHERE f.facebook = :facebook"),
    @NamedQuery(name = "Farm.findByStreet", query = "SELECT f FROM Farm f WHERE f.street = :street"),
    @NamedQuery(name = "Farm.findByCity", query = "SELECT f FROM Farm f WHERE f.city = :city"),
    @NamedQuery(name = "Farm.findByState", query = "SELECT f FROM Farm f WHERE f.state = :state"),
    @NamedQuery(name = "Farm.findByZip", query = "SELECT f FROM Farm f WHERE f.zip = :zip"),
    @NamedQuery(name = "Farm.findBySeason1Date", query = "SELECT f FROM Farm f WHERE f.season1Date = :season1Date"),
    @NamedQuery(name = "Farm.findBySeason1Time", query = "SELECT f FROM Farm f WHERE f.season1Time = :season1Time"),
    @NamedQuery(name = "Farm.findBySeason2Date", query = "SELECT f FROM Farm f WHERE f.season2Date = :season2Date"),
    @NamedQuery(name = "Farm.findBySeason2Time", query = "SELECT f FROM Farm f WHERE f.season2Time = :season2Time"),
    @NamedQuery(name = "Farm.findBySeason3Date", query = "SELECT f FROM Farm f WHERE f.season3Date = :season3Date"),
    @NamedQuery(name = "Farm.findBySeason3Time", query = "SELECT f FROM Farm f WHERE f.season3Time = :season3Time"),
    @NamedQuery(name = "Farm.findByX", query = "SELECT f FROM Farm f WHERE f.x = :x"),
    @NamedQuery(name = "Farm.findByY", query = "SELECT f FROM Farm f WHERE f.y = :y"),
    @NamedQuery(name = "Farm.findByCredit", query = "SELECT f FROM Farm f WHERE f.credit = :credit"),
    @NamedQuery(name = "Farm.findByWic", query = "SELECT f FROM Farm f WHERE f.wic = :wic"),
    @NamedQuery(name = "Farm.findByWICcash", query = "SELECT f FROM Farm f WHERE f.wICcash = :wICcash"),
    @NamedQuery(name = "Farm.findBySfmnp", query = "SELECT f FROM Farm f WHERE f.sfmnp = :sfmnp"),
    @NamedQuery(name = "Farm.findBySnap", query = "SELECT f FROM Farm f WHERE f.snap = :snap"),
    @NamedQuery(name = "Farm.findByOrganic", query = "SELECT f FROM Farm f WHERE f.organic = :organic"),
    @NamedQuery(name = "Farm.findByRating", query = "SELECT f FROM Farm f WHERE f.rating = :rating")})
public class Farm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "FMID")
    private String fmid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "MarketName")
    private String marketName;
    @Size(max = 64)
    @Column(name = "Website")
    private String website;
    @Size(max = 128)
    @Column(name = "Facebook")
    private String facebook;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "street")
    private String street;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "State")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "zip")
    private String zip;
    @Size(max = 32)
    @Column(name = "Season1Date")
    private String season1Date;
    @Size(max = 256)
    @Column(name = "Season1Time")
    private String season1Time;
    @Size(max = 32)
    @Column(name = "Season2Date")
    private String season2Date;
    @Size(max = 64)
    @Column(name = "Season2Time")
    private String season2Time;
    @Size(max = 32)
    @Column(name = "Season3Date")
    private String season3Date;
    @Size(max = 64)
    @Column(name = "Season3Time")
    private String season3Time;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "x")
    private BigDecimal x;
    @Basic(optional = false)
    @NotNull
    @Column(name = "y")
    private BigDecimal y;
    @Size(max = 1)
    @Column(name = "Credit")
    private String credit;
    @Size(max = 1)
    @Column(name = "WIC")
    private String wic;
    @Size(max = 1)
    @Column(name = "WICcash")
    private String wICcash;
    @Size(max = 1)
    @Column(name = "SFMNP")
    private String sfmnp;
    @Size(max = 1)
    @Column(name = "SNAP")
    private String snap;
    @Size(max = 1)
    @Column(name = "Organic")
    private String organic;
    @Column(name = "Rating")
    private BigDecimal rating;

    public Farm() {
    }

    public Farm(String fmid) {
        this.fmid = fmid;
    }

    public Farm(String fmid, String marketName, String street, String city, String state, String zip, BigDecimal x, BigDecimal y) {
        this.fmid = fmid;
        this.marketName = marketName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.x = x;
        this.y = y;
    }

    public String getFmid() {
        return fmid;
    }

    public void setFmid(String fmid) {
        this.fmid = fmid;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getSeason1Date() {
        return season1Date;
    }

    public void setSeason1Date(String season1Date) {
        this.season1Date = season1Date;
    }

    public String getSeason1Time() {
        return season1Time;
    }

    public void setSeason1Time(String season1Time) {
        this.season1Time = season1Time;
    }

    public String getSeason2Date() {
        return season2Date;
    }

    public void setSeason2Date(String season2Date) {
        this.season2Date = season2Date;
    }

    public String getSeason2Time() {
        return season2Time;
    }

    public void setSeason2Time(String season2Time) {
        this.season2Time = season2Time;
    }

    public String getSeason3Date() {
        return season3Date;
    }

    public void setSeason3Date(String season3Date) {
        this.season3Date = season3Date;
    }

    public String getSeason3Time() {
        return season3Time;
    }

    public void setSeason3Time(String season3Time) {
        this.season3Time = season3Time;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getWic() {
        return wic;
    }

    public void setWic(String wic) {
        this.wic = wic;
    }

    public String getWICcash() {
        return wICcash;
    }

    public void setWICcash(String wICcash) {
        this.wICcash = wICcash;
    }

    public String getSfmnp() {
        return sfmnp;
    }

    public void setSfmnp(String sfmnp) {
        this.sfmnp = sfmnp;
    }

    public String getSnap() {
        return snap;
    }

    public void setSnap(String snap) {
        this.snap = snap;
    }

    public String getOrganic() {
        return organic;
    }

    public void setOrganic(String organic) {
        this.organic = organic;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fmid != null ? fmid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Farm)) {
            return false;
        }
        Farm other = (Farm) object;
        if ((this.fmid == null && other.fmid != null) || (this.fmid != null && !this.fmid.equals(other.fmid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "market.mymarket.entityclasses.Farm[ fmid=" + fmid + " ]";
    }
    
}
