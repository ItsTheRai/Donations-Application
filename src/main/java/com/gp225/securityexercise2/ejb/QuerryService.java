/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.ejb;

import com.gp225.securityexercise2.entity.Activity;
import com.gp225.securityexercise2.entity.ActivityDonationObject;
import com.gp225.securityexercise2.entity.Cause;
import com.gp225.securityexercise2.entity.CauseDonationObject;
import com.gp225.securityexercise2.entity.Donation;
import com.gp225.securityexercise2.entity.systemusers.Charity;
import com.gp225.securityexercise2.entity.systemusers.Fundraiser;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raimonds Grismanausks
 */
@Local
public interface QuerryService {
    public List<Activity> getCauseActivityList(Long causeId);

    public List<Cause> getCharityCauseList(String causeId);

    public Charity getCharity(String username);
    
    public Fundraiser getFundraiser(String username);

    public List<Donation> getActivityDonationList(Long activityId);

    public List<Activity> getFundraiserActivityList(String userName);

    public List<CauseDonationObject> getCauseDonationList(String userName);

    public List<Cause> getCauseList();
    
    public List<ActivityDonationObject> getActivityDonationList(Fundraiser fundraisr);

    public boolean isDuplicateEmail(String email);

    public List<Activity> getActivityList();

    public List<Fundraiser> getFundraisers();

    public List<Charity> getCharities();
}