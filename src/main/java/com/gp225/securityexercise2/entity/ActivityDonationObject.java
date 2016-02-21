/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.entity;


import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Raimonds Grismanausks
 */
//temp object used to store activity and donation information
public class ActivityDonationObject {
    long activityId;
    String acivityName;
    Date startDate;
    String description;
    double moneyDonated;

    public ActivityDonationObject() {
    }

    
    
    public ActivityDonationObject(long activityId,String acivityName, Date startDate, String description, double moneyDonated) {
        this.activityId=activityId;
        this.acivityName = acivityName;
        this.startDate = startDate;
        this.description = description;
        this.moneyDonated = moneyDonated;
    }

    public long getActivityId() {
        return activityId;
    }

    
    public String getAcivityName() {
        return acivityName;
    }

    public void setAcivityName(String acivityName) {
        this.acivityName = acivityName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMoneyDonated() {
        return moneyDonated;
    }

    public void setMoneyDonated(double moneyDonated) {
        this.moneyDonated = moneyDonated;
    }

    

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.acivityName);
        hash = 17 * hash + Objects.hashCode(this.startDate);
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.moneyDonated) ^ (Double.doubleToLongBits(this.moneyDonated) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ActivityDonationObject other = (ActivityDonationObject) obj;
        if (!Objects.equals(this.acivityName, other.acivityName)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (Double.doubleToLongBits(this.moneyDonated) != Double.doubleToLongBits(other.moneyDonated)) {
            return false;
        }
        return true;
    }
    
    
}
