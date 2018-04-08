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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.mazad.ejb.daoimp.UserDAOImp;
import com.mazad.ejb.entity.Users;
import com.mazad.ejb.session.AbstractFacade;
import com.mazad.ejb.session.UsersFacade;
import com.mazad.ejb.session.UsersFacadeLocal;

/**
 *
 * @author shalaby
 * 
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserBean {

	@Inject
	public Users u;
	@EJB
	public UsersFacadeLocal fl;

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
			return null;
		} else {
			System.out.println("log in success");
			return "index";
		}


	}
}
