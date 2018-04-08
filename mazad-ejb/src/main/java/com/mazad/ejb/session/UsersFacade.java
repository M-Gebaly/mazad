/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazad.ejb.session;

import com.mazad.ejb.entity.Users;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 *
 * @author M.Gebaly
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {

	@PersistenceContext(unitName ="com.mgebaly_mazad-ejb_ejb_1.0-SNAPSHOTPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UsersFacade() {
		super(Users.class);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public List checkLogin(String name, String pass) {
		System.out.println("in user faceade");
		List rr = new ArrayList<>();
		if (em.equals(null))
		{
			System.out.println("em equal null");
			return rr;
		}
		else
		{
			System.out.println("em is not null");
			
			rr = getEntityManager()
					.createQuery("SELECT u FROM Users u WHERE u.userName = :userName and u.userPassword = :userPassword")
					.setParameter("userName", name).setParameter("userPassword", pass).getResultList();
			return rr;
		}
		
	}

	@SuppressWarnings("rawtypes")
	public List test() {
		return findAll();
	}

}
