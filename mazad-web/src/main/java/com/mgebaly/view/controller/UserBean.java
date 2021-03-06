/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgebaly.view.controller;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.mazad.ejb.daoimp.UserDAOImp;
import com.mazad.ejb.entity.Users;
import com.mazad.ejb.session.AbstractFacade;
import com.mazad.ejb.session.UsersFacade;
import com.mazad.ejb.session.UsersFacadeLocal;
import java.io.Serializable;

/**
 *
 * @author shalaby
 *
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserBean implements Serializable {

    public Users u;
    @EJB
    public UsersFacadeLocal fl;

    public UserBean() {
    }

    public UsersFacadeLocal getFl() {
        return fl;
    }

    public void setFl(UsersFacadeLocal fl) {
        this.fl = fl;
    }

    @PostConstruct
    public void init() {
        u = new Users();

    }

    public Users getU() {
        return u;
    }

    public void setU(Users u) {
        this.u = u;
    }

    public String checkLogin() {
        List<Users> r = new ArrayList<>();

        r = fl.checkLogin(u.getUserName(), u.getUserPassword());
     
        if (r.isEmpty()) {
        	
            System.out.println("log in failed");
            
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("user name or password may be wrong");
            facesContext.addMessage(null, facesMessage);
            
            return "login";
            
        } else {
        	u = r.get(0);
            System.out.println("log in success");
            return "index";
        }
    }

    public String addUser() {
        try {
            Users temp = new Users();
            temp.setUserName(u.getUserName());
            temp.setAuctionLimit(u.getAuctionLimit());
            temp.setUserPassword(u.getUserPassword());
            temp.setUserEmail(u.getUserEmail());
            temp.setUserAddress(u.getUserPassword());
            temp.setCreditCard(u.getCreditCard());
            temp.setUserRole(u.getUserRole());
            fl.create(temp);
            System.out.println("added");
            System.out.println(temp);
            return "login";
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("error in adding");
            return "register";
        }

    }
    
    public void biddingAction (int userId , int AuctionId , float amount)
    {
    	
    }
    
    
}
