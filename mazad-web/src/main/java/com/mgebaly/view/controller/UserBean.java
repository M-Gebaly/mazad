/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgebaly.view.controller;

import java.util.Date;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author shalaby
 * 
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserBean {

    private String password;
    private String email;
    private String name;
    private Date birthDate;
       
    private String locale = "ar";

    public UserBean() {
    }

    public String checkLogin() {
        return null;
    }

    public String register() {
        return null;
    }

    public void validateEmail(FacesContext context, UIComponent component, Object obj) {
        String regexEmail
                = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";

        String enteredEmail = (String) obj;
        if (!enteredEmail.matches(regexEmail)) {
            
            FacesMessage message = new FacesMessage("Validation error", "Invalid Format of Email");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public String changeLocaleMethod() {
        if (locale.equals("ar")) {
            locale = "en_US";
        } else {
            locale = "ar";
        }
        Locale localeObj = new Locale(locale);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(localeObj);
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
