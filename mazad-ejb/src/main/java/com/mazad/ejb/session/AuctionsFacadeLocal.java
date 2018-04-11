/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazad.ejb.session;

import com.mazad.ejb.entity.Auctions;
import com.mazad.ejb.entity.Products;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author M.Gebaly
 */
@Local
public interface AuctionsFacadeLocal {

    void create(Auctions auctions);

    void edit(Auctions auctions);

    void remove(Auctions auctions);

    Auctions find(Object id);

    List<Auctions> findAll();

    List<Auctions> findRange(int[] range);

    int count();
    
}
