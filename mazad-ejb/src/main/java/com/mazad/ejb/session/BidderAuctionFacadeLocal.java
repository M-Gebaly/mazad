/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazad.ejb.session;

import com.mazad.ejb.entity.BidderAuction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author M.Gebaly
 */
@Local
public interface BidderAuctionFacadeLocal {

    void create(BidderAuction bidderAuction);

    void edit(BidderAuction bidderAuction);

    void remove(BidderAuction bidderAuction);

    BidderAuction find(Object id);

    List<BidderAuction> findAll();

    List<BidderAuction> findRange(int[] range);

    int count();
    
    int getWinnerPrice();
    
}
