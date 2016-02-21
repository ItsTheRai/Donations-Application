/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.jsf;

import com.gp225.securityexercise2.ejb.QuerryService;
import com.gp225.securityexercise2.entity.ActivityDonationObject;
import com.gp225.securityexercise2.entity.systemusers.Fundraiser;
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
public class FundraiserProfilePageBean {
    
    @Inject
    QuerryService queryService;
    String username;
    Fundraiser fundraiser;
    //String firstName;
    //String lastName;
    //String MiddleName;
    //String description;
    List<ActivityDonationObject> activityList;

    public FundraiserProfilePageBean() {
    }

    public String getUsername() {
        return username;
    }

    public Fundraiser getFundraiser() {
        return queryService.getFundraiser(this.username);
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public List<ActivityDonationObject> getActivityList() {
        return queryService.getActivityDonationList(queryService.getFundraiser(this.username));
    }

    public void setActivityList(List<ActivityDonationObject> activityList) {
        this.activityList = activityList;
    }
    
    
}