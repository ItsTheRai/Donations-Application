/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.jsf;

import com.gp225.securityexercise2.ejb.exceptions.SameAccountException;
import com.gp225.securityexercise2.ejb.QuerryService;
import com.gp225.securityexercise2.ejb.TransactionService;
import com.gp225.securityexercise2.ejb.RegistrationService;
import com.gp225.securityexercise2.ejb.exceptions.InsufficientFundException;
import com.gp225.securityexercise2.ejb.exceptions.InvalidAccountException;
import com.gp225.securityexercise2.ejb.exceptions.PaymentException;
import com.gp225.securityexercise2.entity.Account;
import com.gp225.securityexercise2.entity.Activity;
import com.gp225.securityexercise2.entity.systemusers.Fundraiser;
import java.io.Serializable;
import static java.lang.reflect.Array.set;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import static javax.servlet.jsp.jstl.core.Config.set;
import javax.transaction.SystemException;

/**
 *
 * @author Raimonds Grismanausks
 */
@ManagedBean
@ViewScoped
public class DonationRegistrationBean implements Serializable{
    String userName;
    Fundraiser donationFundraiser;
    Activity donationActivity;
    float donationAmount;
    Account fromAccount;
    Account toAccount;
    //for the drop down menu
    private Activity selectedStatus;
    private List<Activity> statusList = null;
    
    @EJB
    RegistrationService regSrv;
    @EJB
    TransactionService transSrv;
    @EJB
    QuerryService querrySrv;
    
    public DonationRegistrationBean(){
        
    }
    
    

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public Fundraiser getDonationFundraiser() {
        return querrySrv.getFundraiser(userName);
    }

    public void setDonationFundraiser(Fundraiser fundraiser) {
        this.donationFundraiser = fundraiser;
    }
    
    public Activity getDonationActivity() {
        return donationActivity;
    }

    public void setDonationActivity(Activity donationActivity) {
        this.donationActivity = donationActivity;
    }
    
     public float getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(float donationAmount) {
        this.donationAmount = donationAmount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public Activity getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(Activity selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

    //public List<Activity> getStatusList() {
    //    return querrySrv.getCauseActivityList(this.userName);
   // }
    
    public List<Activity> getStatusList(){
        //this is wrong should return all the activities
        return querrySrv.getActivityList();
    }

    public void setStatusList(List<Activity> list) {
        this.statusList = list;
    }

    public String registerDonation() throws InvalidAccountException, InsufficientFundException, PaymentException, SystemException, SameAccountException{
        Fundraiser f = querrySrv.getFundraiser(this.userName);
        Account fromAcc = f.getAccount();
        Activity t = this.selectedStatus;
        Account toAcc = t.getCause().getCharity().getAccount();
        try{
            //check if activity date is greater than current date
            Calendar toDate = Calendar.getInstance();
            Calendar nowDate = Calendar.getInstance();
            toDate.set(t.getStartDate().getYear(),t.getStartDate().getMonth(),t.getStartDate().getDay());  
            if(!toDate.before(nowDate)) {
                String message = "This activity has expired, please donate to a different activity";
                FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
                return null;
            }
            
            if(this.donationAmount>500){
                String message = "You can't donate more than £500 for a singe activity";
                FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
                return null;
            }
            if(f.getActivities().contains(t)){
                String message = "You can't donate to your own activity";
                FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
                return null;
            }
        transSrv.makeDonation(fromAcc, this.donationAmount, toAcc);
        regSrv.registerDonation(this.donationAmount,f,t);
        }catch(InsufficientFundException e){
            String message = "Insufficient funds";
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return null;
        }catch(InvalidAccountException | PaymentException | SystemException e){
            String message = "something went wrong, please try angain";
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return null;
        }
        return "/fundraiser/index";
    }

    public String registerAnonymousDonation() throws InvalidAccountException, InsufficientFundException, PaymentException, SystemException, SameAccountException{
        Fundraiser f = querrySrv.getFundraiser(this.userName);
        Account fromAcc = f.getAccount();
        Activity t = this.selectedStatus;
        Account toAcc = t.getCause().getCharity().getAccount();
        try{
            if(this.donationAmount>500){
                String message = "You can't donate more than £500 for a singe activity";
                FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
                return null;
            }
            if(f.getActivities().contains(t)){
                String message = "You can't donate to your own activity";
                FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
                return null;
            }
        transSrv.makeDonation(fromAcc, this.donationAmount, toAcc);
        regSrv.registerDonation(this.donationAmount,null,t);
        }catch(InsufficientFundException e){
            String message = "Insufficient funds";
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return null;
        }catch(InvalidAccountException | PaymentException | SystemException e){
            String message = "something went wrong, please try angain";
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return null;
        }
        return "/fundraiser/index";
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.userName);
        hash = 23 * hash + Objects.hashCode(this.donationFundraiser);
        hash = 23 * hash + Objects.hashCode(this.donationActivity);
        hash = 23 * hash + Float.floatToIntBits(this.donationAmount);
        hash = 23 * hash + Objects.hashCode(this.fromAccount);
        hash = 23 * hash + Objects.hashCode(this.toAccount);
        hash = 23 * hash + Objects.hashCode(this.regSrv);
        hash = 23 * hash + Objects.hashCode(this.transSrv);
        hash = 23 * hash + Objects.hashCode(this.querrySrv);
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
        final DonationRegistrationBean other = (DonationRegistrationBean) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.donationFundraiser, other.donationFundraiser)) {
            return false;
        }
        if (!Objects.equals(this.donationActivity, other.donationActivity)) {
            return false;
        }
        if (Float.floatToIntBits(this.donationAmount) != Float.floatToIntBits(other.donationAmount)) {
            return false;
        }
        if (!Objects.equals(this.fromAccount, other.fromAccount)) {
            return false;
        }
        if (!Objects.equals(this.toAccount, other.toAccount)) {
            return false;
        }
        if (!Objects.equals(this.regSrv, other.regSrv)) {
            return false;
        }
        if (!Objects.equals(this.transSrv, other.transSrv)) {
            return false;
        }
        if (!Objects.equals(this.querrySrv, other.querrySrv)) {
            return false;
        }
        return true;
    }
   
    
}
