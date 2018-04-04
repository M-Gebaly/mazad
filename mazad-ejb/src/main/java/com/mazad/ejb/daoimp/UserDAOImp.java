/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazad.ejb.daoimp;

import com.mazad.ejb.daoint.UserDAOInt;
import com.mazad.ejb.entity.Users;
import com.mazad.ejb.session.UsersFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author M.Gebaly
 */
public class UserDAOImp implements UserDAOInt{

    UsersFacade usersFacade = lookupUsersFacadeBean();
     
    
    public boolean addToDB (Users u)
    {
        usersFacade.create(u);
        return  true;
       }

    private UsersFacade lookupUsersFacadeBean() {
        try {
            Context c = new InitialContext();
            return (UsersFacade) c.lookup("java:global/com.mgebaly_mazad-ear_ear_1.0-SNAPSHOT/com.mgebaly_mazad-ejb_ejb_1.0-SNAPSHOT/UsersFacade!com.mazad.ejb.session.UsersFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
