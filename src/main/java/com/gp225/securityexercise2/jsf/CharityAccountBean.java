/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.jsf;

import com.gp225.securityexercise2.ejb.QuerryService;
import com.gp225.securityexercise2.ejb.RegistrationService;
import com.gp225.securityexercise2.entity.Cause;
import com.gp225.securityexercise2.entity.CauseDonationObject;
import com.gp225.securityexercise2.entity.systemusers.Charity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Raimonds Grismanausks
 */
@ManagedBean
@ViewScoped
public class CharityAccountBean implements Serializable{
    String userName;
    
    String accountNumber;
    
    @Inject
    RegistrationService regService;
    @Inject
    QuerryService querryService;
    
    List<CauseDonationObject> causeDonationObject;
    
    public CharityAccountBean(){
        
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    public List<Cause> getCauseList(){
        return querryService.getCharityCauseList(this.userName);
    }
    
    public Charity getCurrentCharity(){
        return querryService.getCharity(userName);
    }
    
    public List<CauseDonationObject> getCauseDonationObject(){
        return querryService.getCauseDonationList(this.userName);
    }
}