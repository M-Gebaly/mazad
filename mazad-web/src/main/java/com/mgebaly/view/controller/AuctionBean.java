package com.mgebaly.view.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mazad.ejb.entity.Auctions;
import com.mazad.ejb.entity.Products;
import com.mazad.ejb.entity.Users;
import com.mazad.ejb.session.AuctionsFacadeLocal;
import com.mazad.ejb.session.ProductsFacadeLocal;

@ManagedBean(name = "auction")
@SessionScoped
public class AuctionBean {

	@Inject
	Auctions auction;
	
	AuctionsFacadeLocal AFL;
	
	List<Auctions> AuctionsList;

	private DataModel<Auctions> model;
	
	List<Products> productList;

	
	
	private AuctionsFacadeLocal getEJB() throws NamingException {
	    InitialContext context = new InitialContext();
	    return (AuctionsFacadeLocal) context.lookup("java:global/mazad-ear/mazad-ejb-1.0-SNAPSHOT/AuctionsFacade!com.mazad.ejb.session.AuctionsFacadeLocal");
	}
	
	public AuctionBean() {
		// TODO Auto-generated constructor stub
	   
		try {
			AFL = getEJB();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println("can't get interface ");
		}
		AuctionsList = new ArrayList<>();
	    AuctionsList=AFL.findAll();
	    model= new ListDataModel<>(AuctionsList);
	    
	}
	
	//getter and setter 
	
	public List<Auctions> getAuctionsList() {
		return AuctionsList;
	}

	public void setAuctionsList(List<Auctions> auctionsList) {
		AuctionsList = auctionsList;
	}

	public DataModel<Auctions> getModel() {
		return model;
	}

	public void setModel(DataModel<Auctions> model) {
		this.model = model;
	}

	public Auctions getAuction() {
		return auction;
	}

	public void setAuction(Auctions auction) {
		this.auction = auction;
	}

	public AuctionsFacadeLocal getAFL() {
		return AFL;
	}

	public void setAFL(AuctionsFacadeLocal aFL) {
		AFL = aFL;
	}


	public List<Products> getProductList() {
		return productList;
	}


	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}
	
	
}
