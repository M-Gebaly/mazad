/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazad.ejb.session;

import com.mazad.ejb.entity.BidderAuction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author M.Gebaly
 */
@Stateless
public class BidderAuctionFacade extends AbstractFacade<BidderAuction> implements BidderAuctionFacadeLocal {

	@PersistenceContext(unitName = "com.mgebaly_mazad-ejb_ejb_1.0-SNAPSHOTPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public BidderAuctionFacade() {
		super(BidderAuction.class);
	} 

	@Override
	public int getWinnerPrice() {
		System.out.println("searching for winner");
		List<BigInteger> list = new ArrayList<>();
		list = getEntityManager().createQuery("select p.bidAmount from BidderAuction p where p.winner = 1")
				.getResultList();
		if(list.size()==0)
		{
			return 0;
		}
		else
		{
			System.out.println(list.get(0));
			return list.get(0).intValue();
		}
		
	}

}
