/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazad.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author M.Gebaly
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId")
    , @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName")
    , @NamedQuery(name = "Users.findByUserPassword", query = "SELECT u FROM Users u WHERE u.userPassword = :userPassword")
    , @NamedQuery(name = "Users.findByUserEmail", query = "SELECT u FROM Users u WHERE u.userEmail = :userEmail")
    , @NamedQuery(name = "Users.findByUserAddress", query = "SELECT u FROM Users u WHERE u.userAddress = :userAddress")
    , @NamedQuery(name = "Users.findByAuctionLimit", query = "SELECT u FROM Users u WHERE u.auctionLimit = :auctionLimit")
    , @NamedQuery(name = "Users.findByCreditCard", query = "SELECT u FROM Users u WHERE u.creditCard = :creditCard")
    , @NamedQuery(name = "Users.findByUserRole", query = "SELECT u FROM Users u WHERE u.userRole = :userRole")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private BigDecimal userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USER_NAME")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Size(max = 100)
    @Column(name = "USER_ADDRESS")
    private String userAddress;
    @Column(name = "AUCTION_LIMIT")
    private BigInteger auctionLimit;
    @Size(max = 20)
    @Column(name = "CREDIT_CARD")
    private String creditCard;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ROLE")
    private BigInteger userRole;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<BidderAuction> bidderAuctionList;
    @OneToMany(mappedBy = "ownerId")
    private List<Auctions> auctionsList;
    @OneToMany(mappedBy = "supplerId")
    private List<Auctions> auctionsList1;

    public Users() {
    }

    public Users(BigDecimal userId) {
        this.userId = userId;
    }

    public Users(BigDecimal userId, String userName, String userPassword, String userEmail, BigInteger userRole) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userRole = userRole;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public BigInteger getAuctionLimit() {
        return auctionLimit;
    }

    public void setAuctionLimit(BigInteger auctionLimit) {
        this.auctionLimit = auctionLimit;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public BigInteger getUserRole() {
        return userRole;
    }

    public void setUserRole(BigInteger userRole) {
        this.userRole = userRole;
    }

    @XmlTransient
    public List<BidderAuction> getBidderAuctionList() {
        return bidderAuctionList;
    }

    public void setBidderAuctionList(List<BidderAuction> bidderAuctionList) {
        this.bidderAuctionList = bidderAuctionList;
    }

    @XmlTransient
    public List<Auctions> getAuctionsList() {
        return auctionsList;
    }

    public void setAuctionsList(List<Auctions> auctionsList) {
        this.auctionsList = auctionsList;
    }

    @XmlTransient
    public List<Auctions> getAuctionsList1() {
        return auctionsList1;
    }

    public void setAuctionsList1(List<Auctions> auctionsList1) {
        this.auctionsList1 = auctionsList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mazad.ejb.entity.Users[ userId=" + userId + " ]";
    }
    
}
