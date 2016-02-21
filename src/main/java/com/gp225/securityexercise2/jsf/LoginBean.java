package com.gp225.securityexercise2.jsf;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@ManagedBean
public class LoginBean implements Serializable {
    private String username;
  private String password;

  public String getUsername() {
    return this.username;
  }

  public void setUserName(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


//  public String login () {
//    FacesContext context = FacesContext.getCurrentInstance();
//    
//    javax.servlet.http.HttpServletRequest request = (HttpServletRequest) 
//        context.getExternalContext().getRequest();
//    try {
//      request.login(this.username, this.password);
//    } catch (ServletException e) {
//      
//      context.addMessage(null, new FacesMessage("Login failed."));
//      return "error";
//    }
//    return "admin/index";
  //}
    
    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            //this method will disassociate the principal from the session (effectively logging him/her out)
            request.logout();
            context.addMessage(null, new FacesMessage("User is logged out"));
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
    }
}