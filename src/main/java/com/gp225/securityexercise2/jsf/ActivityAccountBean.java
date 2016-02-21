/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.jsf;

import com.gp225.securityexercise2.ejb.QuerryService;
import com.gp225.securityexercise2.ejb.RegistrationService;
import com.gp225.securityexercise2.entity.Donation;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Raimonds Grismanausks
 */
@ManagedBean
@ViewScoped
public class ActivityAccountBean {
    Long activityId;
    String activityName;
    
    List<Donation> activityDonationList;
    String accountNumber;
    @Inject
    RegistrationService regService;
    @Inject
    QuerryService querryService;
    
    public ActivityAccountBean(){
        
    }       

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Donation> getActivityDonationList(){
        return querryService.getActivityDonationList(activityId);
    }
}