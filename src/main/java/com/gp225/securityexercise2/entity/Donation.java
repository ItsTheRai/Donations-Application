/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.entity;

import com.gp225.securityexercise2.entity.systemusers.Fundraiser;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author Raimonds Grismanausks
 */
@Entity
@XmlRootElement
public class Donation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="DONATION_ID")
    private Long donationId;
    
    @NotNull
    @Column(name ="DONATION_AMOUNT")
    private double donationAmount;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @NotNull
    @Column(name="TRANSACTION_DATE")
    Date transactionDate;
    
    public Donation(){
    }

    public Donation(double donationAmount, Fundraiser fundraiser, Activity activity) {
        this.donationAmount = donationAmount;
        transactionDate = new Date();
        this.fundraiser = fundraiser;
        this.activity=activity;
 
    }
    
    @ManyToOne(optional=true,fetch=FetchType.EAGER)
    @JoinColumn(name = "USER_NAME",referencedColumnName="USER_NAME")
    private Fundraiser fundraiser;
    
    @ManyToOne(optional=false,fetch=FetchType.EAGER)
    @JoinColumn(name = "ACTIVITY_ID",referencedColumnName="ACTIVITY_ID")
    private Activity activity;

    @Immutable
    public Long getDonationId() {
        return donationId;
    }

    @Immutable
    public double getDonationAmount() {
        return donationAmount;
    }

    @Immutable
    public Date getTransactionDate() {
        return transactionDate;
    }

    
    public Fundraiser getFundraiser() {
        return fundraiser;
    }

   
    public Activity getActivity() {
        return activity;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.donationId);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.donationAmount) ^ (Double.doubleToLongBits(this.donationAmount) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.transactionDate);
        hash = 59 * hash + Objects.hashCode(this.fundraiser);
        hash = 59 * hash + Objects.hashCode(this.activity);
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
        final Donation other = (Donation) obj;
        if (!Objects.equals(this.donationId, other.donationId)) {
            return false;
        }
        if (Double.doubleToLongBits(this.donationAmount) != Double.doubleToLongBits(other.donationAmount)) {
            return false;
        }
        if (!Objects.equals(this.transactionDate, other.transactionDate)) {
            return false;
        }
        if (!Objects.equals(this.fundraiser, other.fundraiser)) {
            return false;
        }
        if (!Objects.equals(this.activity, other.activity)) {
            return false;
        }
        return true;
    }

    
    
    
}