/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.jsf;

import com.gp225.securityexercise2.ejb.QuerryService;
import com.gp225.securityexercise2.ejb.RegistrationService;
import com.gp225.securityexercise2.entity.Activity;
import com.gp225.securityexercise2.entity.CauseDonationObject;
import com.gp225.securityexercise2.entity.Donation;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Raimonds Grismanausks
 */
public class CauseAccountBean {
    String userName;
    
    Long causeId;
    
    String accountNumber;
    
    List<Activity> causeActivityList;
    
    @Inject
    RegistrationService regService;
    @Inject
    QuerryService querryService;
    
    public CauseAccountBean(){
        
    }

    public String getUserName() {
        return userName;
    }

    public Long getCauseId() {
        return causeId;
    }

    public void setCauseId(Long causeId) {
        this.causeId = causeId;
    }
    

    public String getAccountNumber() {
        return accountNumber;
    }
    
    public List<Activity> getCauseActivityList() {
        return querryService.getCauseActivityList(causeId);
    }
}