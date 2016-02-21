/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.jsf;

import com.gp225.securityexercise2.ejb.QuerryService;
import com.gp225.securityexercise2.ejb.RegistrationService;
import com.gp225.securityexercise2.entity.systemusers.Charity;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Raimonds Grismanausks
 */

@ManagedBean
@ViewScoped
public class CauseRegistrationBean implements Serializable{
    
    String userName;
    String causeName;
    String causeDescription;
    Charity causeCharity;
    
    @Inject
    RegistrationService userSrv;
    @Inject
    QuerryService querrySrv;
    
    public CauseRegistrationBean(){
    }
    
    
    public String registerCause(){
        userSrv.registerCause(this.causeName, this.causeDescription, querrySrv.getCharity(userName));
        return "/charity/index";
    }

    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCauseName() {
        return causeName;
    }

    public void setCauseName(String causeName) {
        this.causeName = causeName;
    }

    
    public String getCauseDescription() {
        return causeDescription;
    }

    public void setCauseDescription(String causeDescription) {
        this.causeDescription = causeDescription;
    }

    public Charity getCauseCharity() {
        return querrySrv.getCharity(userName);
    }

    public void setCauseCharity(Charity charity) {
        this.causeCharity = charity;
    }
    
    
}
