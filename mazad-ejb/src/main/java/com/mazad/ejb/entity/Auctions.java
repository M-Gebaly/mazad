/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazad.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author M.Gebaly
 */
@Entity
@Table(name = "AUCTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auctions.findAll", query = "SELECT a FROM Auctions a")
    , @NamedQuery(name = "Auctions.findByAuctionId", query = "SELECT a FROM Auctions a WHERE a.auctionId = :auctionId")
    , @NamedQuery(name = "Auctions.findByAuctionName", query = "SELECT a FROM Auctions a WHERE a.auctionName = :auctionName")
    , @NamedQuery(name = "Auctions.findByEndDate", query = "SELECT a FROM Auctions a WHERE a.endDate = :endDate")
    , @NamedQuery(name = "Auctions.findByAprove", query = "SELECT a FROM Auctions a WHERE a.aprove = :aprove")})
public class Auctions implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUCTION_ID")
    private BigDecimal auctionId;
    @Size(max = 50)
    @Column(name = "AUCTION_NAME")
    private String auctionName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "APROVE")
    private BigInteger aprove;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auctions")
    private List<BidderAuction> bidderAuctionList;
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private Users ownerId;
    @JoinColumn(name = "SUPPLER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private Users supplerId;
    @OneToMany(mappedBy = "auctionId")
    private List<Products> productsList;

    public Auctions() {
    }

    public Auctions(BigDecimal auctionId) {
        this.auctionId = auctionId;
    }

    public Auctions(BigDecimal auctionId, Date endDate) {
        this.auctionId = auctionId;
        this.endDate = endDate;
    }

    public BigDecimal getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(BigDecimal auctionId) {
        this.auctionId = auctionId;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigInteger getAprove() {
        return aprove;
    }

    public void setAprove(BigInteger aprove) {
        this.aprove = aprove;
    }

    @XmlTransient
    public List<BidderAuction> getBidderAuctionList() {
        return bidderAuctionList;
    }

    public void setBidderAuctionList(List<BidderAuction> bidderAuctionList) {
        this.bidderAuctionList = bidderAuctionList;
    }

    public Users getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Users ownerId) {
        this.ownerId = ownerId;
    }

    public Users getSupplerId() {
        return supplerId;
    }

    public void setSupplerId(Users supplerId) {
        this.supplerId = supplerId;
    }

    @XmlTransient
    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auctionId != null ? auctionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auctions)) {
            return false;
        }
        Auctions other = (Auctions) object;
        if ((this.auctionId == null && other.auctionId != null) || (this.auctionId != null && !this.auctionId.equals(other.auctionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mazad.ejb.entity.Auctions[ auctionId=" + auctionId + " ]";
    }
    
}
