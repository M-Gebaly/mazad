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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author M.Gebaly
 */
@Entity
@Table(name = "BIDDER_AUCTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BidderAuction.findAll", query = "SELECT b FROM BidderAuction b")
    , @NamedQuery(name = "BidderAuction.findByAuctionId", query = "SELECT b FROM BidderAuction b WHERE b.bidderAuctionPK.auctionId = :auctionId")
    , @NamedQuery(name = "BidderAuction.findByBidderId", query = "SELECT b FROM BidderAuction b WHERE b.bidderAuctionPK.bidderId = :bidderId")
    , @NamedQuery(name = "BidderAuction.findByBidAmount", query = "SELECT b FROM BidderAuction b WHERE b.bidAmount = :bidAmount")
    , @NamedQuery(name = "BidderAuction.findByWinner", query = "SELECT b FROM BidderAuction b WHERE b.winner = :winner")})
public class BidderAuction implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BidderAuctionPK bidderAuctionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BID_AMOUNT")
    private BigInteger bidAmount;
    @Column(name = "WINNER")
    private BigInteger winner;
    @JoinColumn(name = "AUCTION_ID", referencedColumnName = "AUCTION_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Auctions auctions;
    @JoinColumn(name = "BIDDER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public BidderAuction() {
    }

    public BidderAuction(BidderAuctionPK bidderAuctionPK) {
        this.bidderAuctionPK = bidderAuctionPK;
    }

    public BidderAuction(BidderAuctionPK bidderAuctionPK, BigInteger bidAmount) {
        this.bidderAuctionPK = bidderAuctionPK;
        this.bidAmount = bidAmount;
    }

    public BidderAuction(BigInteger auctionId, BigInteger bidderId) {
        this.bidderAuctionPK = new BidderAuctionPK(auctionId, bidderId);
    }

    public BidderAuctionPK getBidderAuctionPK() {
        return bidderAuctionPK;
    }

    public void setBidderAuctionPK(BidderAuctionPK bidderAuctionPK) {
        this.bidderAuctionPK = bidderAuctionPK;
    }

    public BigInteger getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigInteger bidAmount) {
        this.bidAmount = bidAmount;
    }

    public BigInteger getWinner() {
        return winner;
    }

    public void setWinner(BigInteger winner) {
        this.winner = winner;
    }

    public Auctions getAuctions() {
        return auctions;
    }

    public void setAuctions(Auctions auctions) {
        this.auctions = auctions;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bidderAuctionPK != null ? bidderAuctionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BidderAuction)) {
            return false;
        }
        BidderAuction other = (BidderAuction) object;
        if ((this.bidderAuctionPK == null && other.bidderAuctionPK != null) || (this.bidderAuctionPK != null && !this.bidderAuctionPK.equals(other.bidderAuctionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mazad.ejb.entity.BidderAuction[ bidderAuctionPK=" + bidderAuctionPK + " ]";
    }
    
}
