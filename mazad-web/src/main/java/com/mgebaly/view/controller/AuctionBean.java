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
import com.mazad.ejb.entity.BidderAuction;
import com.mazad.ejb.entity.BidderAuctionPK;
import com.mazad.ejb.entity.Products;
import com.mazad.ejb.entity.Users;
import com.mazad.ejb.session.AuctionsFacadeLocal;
import com.mazad.ejb.session.BidderAuctionFacadeLocal;
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
			BAFL = getBidderAuctionEjb();
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
        auction.getProductsList();
        productsModel = new ListDataModel<>(auction.getProductsList());
        
        auction.getProductsList().get(0).getProductName();
        System.out.println(auction.getAuctionId());
        return "viewProducts";
    }
    
    public String loadList(){
	AuctionsList=AFL.findAll();
	model= new ListDataModel<>(AuctionsList);
        return "index";
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
    
    
    
    // bidding 
    
    //java:global/mazad-ear/mazad-ejb-1.0-SNAPSHOT/BidderAuctionFacade!com.mazad.ejb.session.BidderAuctionFacadeLocal
    private BidderAuctionFacadeLocal getBidderAuctionEjb() throws NamingException {
	    InitialContext context = new InitialContext();
	    return (BidderAuctionFacadeLocal) context.lookup("java:global/mazad-ear/mazad-ejb-1.0-SNAPSHOT/BidderAuctionFacade!com.mazad.ejb.session.BidderAuctionFacadeLocal");
	}
    
    BidderAuctionFacadeLocal BAFL;
    BigInteger amount;
      
    public BidderAuctionFacadeLocal getBAFL() {
		return BAFL;
	}
	public void setBAFL(BidderAuctionFacadeLocal bAFL) {
		BAFL = bAFL;
	}
	
	
	public BigInteger getAmount() {
		return amount;
	}
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}
	public void userBid(String id)
    {
    	
		auction = AFL.find(BigDecimal.valueOf(Double.parseDouble(id)));
		BidderAuction bidderAuction = new BidderAuction();
    	
    	
    	bidderAuction.setAuctions(auction);
    	System.out.println(bidderAuction.getAuctions().getAuctionId());
    	
    	bidderAuction.setUsers(userBean.u);
    	System.out.println(bidderAuction.getUsers().getUserName());
    	
    	bidderAuction.setBidAmount(amount);
    	System.err.println(bidderAuction.getBidAmount());
    	
    	BidderAuctionPK pk = new BidderAuctionPK();
    	pk.setAuctionId(auction.getAuctionId().toBigInteger());
    	pk.setBidderId(userBean.u.getUserId().toBigInteger());
    	
    	bidderAuction.setBidderAuctionPK(pk);
    	System.out.println(bidderAuction.getBidderAuctionPK());
    	
    	
     
    	// winner part
    	
    	List<BidderAuction> allAuction = new ArrayList<>();
    	allAuction= BAFL.findAll();
    	for (BidderAuction ba : allAuction) {
			if(amount.intValue() > ba.getBidAmount().intValue()&& bidderAuction.getAuctions().getAuctionId()==ba.getAuctions().getAuctionId())
			{
				ba.setWinner(BigInteger.ZERO);
				bidderAuction.setWinner(BigInteger.ONE);
				BAFL.edit(ba);
			}
			
		}
        	if(BAFL.find(pk)==null)
        	{
        		BAFL.create(bidderAuction);
        	
        	}
        	else
        	{
        		BAFL.edit(bidderAuction);
        	}
    	
    	System.out.println("bid succrsufully with " + bidderAuction.getBidAmount());
    	amount=null;
    }
	
	int winner=0;
	
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public void getWinnerPrice(Auctions id)
	{
		winner= BAFL.getWinnerPrice(id);
	}
    
    
}
