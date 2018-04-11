package com.mgebaly.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mazad.ejb.entity.Products;
import com.mazad.ejb.session.ProductsFacadeLocal;

@ManagedBean(name = "product")
@SessionScoped
public class ProductsBean {

	@Inject
	Products product;
	
   ProductsFacadeLocal facadeLocal;
   
   private DataModel<Products> dataModel;
   
   List<Products> list ;
   
   
   private ProductsFacadeLocal getEJB() throws NamingException {
	    InitialContext context = new InitialContext();
	    return (ProductsFacadeLocal) context.lookup("java:global/mazad-ear/mazad-ejb-1.0-SNAPSHOT/ProductsFacade!com.mazad.ejb.session.ProductsFacadeLocal");
	}
   
	public ProductsBean() {
		// TODO Auto-generated constructor stub

		try {

			facadeLocal = getEJB();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("can't get interface ");
		}

		 list= new ArrayList<>();
		 list = facadeLocal.findAll();
		 dataModel= new ListDataModel<>(list);
	}
	
	

	
	
	
	
	//setter and getter
   
	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public ProductsFacadeLocal getFacadeLocal() {
		return facadeLocal;
	}

	public void setFacadeLocal(ProductsFacadeLocal facadeLocal) {
		this.facadeLocal = facadeLocal;
	}

	public DataModel<Products> getDataModel() {
		return dataModel;
	}

	public void setDataModel(DataModel<Products> dataModel) {
		this.dataModel = dataModel;
	}

	public List<Products> getList() {
		return list;
	}

	public void setList(List<Products> list) {
		this.list = list;
	}
	
}
