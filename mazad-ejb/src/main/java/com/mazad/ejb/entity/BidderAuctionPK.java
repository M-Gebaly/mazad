/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazad.ejb.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author M.Gebaly
 */
@Embeddable
public class BidderAuctionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "AUCTION_ID")
    private BigInteger auctionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BIDDER_ID")
    private BigInteger bidderId;

    public BidderAuctionPK() {
    }

    public BidderAuctionPK(BigInteger auctionId, BigInteger bidderId) {
        this.auctionId = auctionId;
        this.bidderId = bidderId;
    }

    public BigInteger getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(BigInteger auctionId) {
        this.auctionId = auctionId;
    }

    public BigInteger getBidderId() {
        return bidderId;
    }

    public void setBidderId(BigInteger bidderId) {
        this.bidderId = bidderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auctionId != null ? auctionId.hashCode() : 0);
        hash += (bidderId != null ? bidderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BidderAuctionPK)) {
            return false;
        }
        BidderAuctionPK other = (BidderAuctionPK) object;
        if ((this.auctionId == null && other.auctionId != null) || (this.auctionId != null && !this.auctionId.equals(other.auctionId))) {
            return false;
        }
        if ((this.bidderId == null && other.bidderId != null) || (this.bidderId != null && !this.bidderId.equals(other.bidderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mazad.ejb.entity.BidderAuctionPK[ auctionId=" + auctionId + ", bidderId=" + bidderId + " ]";
    }
    
}
