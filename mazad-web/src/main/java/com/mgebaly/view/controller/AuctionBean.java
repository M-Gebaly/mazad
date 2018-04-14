package com.mgebaly.view.controller;


import java.util.ArrayList;
import java.util.Date;
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
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "auction")
@SessionScoped
public class AuctionBean implements Serializable{

	Auctions auction;
	
	AuctionsFacadeLocal AFL;
	
	List<Auctions> AuctionsList;

	private DataModel<Auctions> model;
	
	List<Products> productList;
        
        private DataModel<Products> productsModel;
	
	String minuteLefted;
	
	
		public String getMinuteLefted() {
		return minuteLefted;
	}
	public void setMinuteLefted(String minuteLefted) {
		this.minuteLefted = minuteLefted;
	}

		@ManagedProperty(value = "#{user}")
        private UserBean userBean;

	
	
	private AuctionsFacadeLocal getEJB() throws NamingException {
	    InitialContext context = new InitialContext();
	    return (AuctionsFacadeLocal) context.lookup("java:global/mazad-ear/mazad-ejb-1.0-SNAPSHOT/AuctionsFacade!com.mazad.ejb.session.AuctionsFacadeLocal");
	}
        @PostConstruct
	public void init() {
            auction = new Auctions();
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
	
	public void getTimeAvailable(Date t) {
		Date current = new Date();

		Duration d = Duration.between(current.toInstant(),t.toInstant());



		minuteLefted =(int)d.toMillis()/1000 +": second" +"   "+(int)d.toMinutes()+": minutes"+"  " +d.toDays()+": day";
         // minuteLefted = d.toString();
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

    /**
     * @return the userBean
     */
    public UserBean getUserBean() {
        return userBean;
    }

    /**
     * @param userBean the userBean to set
     */
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String addAuction() {
        Auctions auc = new Auctions();
        System.out.println(auction.getEndDate());
        
        auc.setAuctionName(auction.getAuctionName());
        auc.setAprove(BigInteger.ZERO);
        auc.setOwnerId(userBean.u);
        auc.setEndDate(auction.getEndDate());
        
         System.out.println(auc.getEndDate());
        AFL.create(auc);
        auction.setAuctionId(auc.getAuctionId());
        System.out.println("added");
        return "addProduct";
    }
    
    public String viewProduct(String id){
        System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        auction = AFL.find(BigDecimal.valueOf(Double.parseDouble(id)));
        productsModel = new ListDataModel<>(auction.getProductsList());
        System.out.println(auction.getAuctionId());
        
        System.out.println(productsModel.getRowData().getProductName());

        return "viewProducts";
    }

    /**
     * @return the productsModel
     */
    public DataModel<Products> getProductsModel() {
        return productsModel;
    }

    /**
     * @param productsModel the productsModel to set
     */
    public void setProductsModel(DataModel<Products> productsModel) {
        this.productsModel = productsModel;
    }
}
