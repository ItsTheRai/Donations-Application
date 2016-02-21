/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.entity;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Raimonds Grismanausks
 */
//temp object used to store cause and donation information
public class CauseDonationObject {
    Long causeId;
    String couseIdn;
    Number couseIden;
    
    String causeName;
    String causeDescription;
    double donationAmount;

    public CauseDonationObject(){
        
    }
    public CauseDonationObject(Long causeId, String causeName, String causeDescription,double donationAmount) {
        this.causeId = causeId;
        this.causeName=causeName;
        this.causeDescription=causeDescription;
        this.donationAmount=donationAmount;
    }
    
    public Long getCauseId() {
        return causeId;
    }

    public void setCauseId(Long causeId) {
        this.causeId = causeId;
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

    public double getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(double donationAmount) {
        this.donationAmount = donationAmount;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.causeId);
        hash = 61 * hash + Objects.hashCode(this.couseIdn);
        hash = 61 * hash + Objects.hashCode(this.couseIden);
        hash = 61 * hash + Objects.hashCode(this.causeName);
        hash = 61 * hash + Objects.hashCode(this.causeDescription);
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.donationAmount) ^ (Double.doubleToLongBits(this.donationAmount) >>> 32));
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
        final CauseDonationObject other = (CauseDonationObject) obj;
        if (!Objects.equals(this.causeId, other.causeId)) {
            return false;
        }
        if (!Objects.equals(this.couseIdn, other.couseIdn)) {
            return false;
        }
        if (!Objects.equals(this.couseIden, other.couseIden)) {
            return false;
        }
        if (!Objects.equals(this.causeName, other.causeName)) {
            return false;
        }
        if (!Objects.equals(this.causeDescription, other.causeDescription)) {
            return false;
        }
        if (Double.doubleToLongBits(this.donationAmount) != Double.doubleToLongBits(other.donationAmount)) {
            return false;
        }
        return true;
    }
    
    
}