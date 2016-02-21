/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.jsf;

import com.gp225.securityexercise2.ejb.QuerryService;
import com.gp225.securityexercise2.ejb.TransactionService;
import com.gp225.securityexercise2.ejb.RegistrationService;
import com.gp225.securityexercise2.entity.Cause;
import com.gp225.securityexercise2.entity.systemusers.Fundraiser;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author Raimonds Grismanausks
 */
@ManagedBean
@ViewScoped
public class ActivityRegistrationBean implements Serializable{
    @Inject
    QuerryService querrySrv;
    @Inject
    RegistrationService regSrv;
    @Inject
    TransactionService tranSrv;
    
    
    private String userName;
    
    
    
    private Fundraiser fundraiser;
    private Cause cause;
    private String description;
    private String activityName;
    private Date activityDate;
    private String activityDateString;

    private Cause selectedStatus;
    private List<Cause> statusList = null;
 
    public ActivityRegistrationBean() {
    }

    public String getActivityDateString() {
        return activityDateString;
    }

    public void setActivityDateString(String activityDateString) {
        this.activityDateString = activityDateString;
    }

    
    public Date getActivityDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse(this.activityDateString);
        return date;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    
    public Fundraiser getFundraiser() {
        return querrySrv.getFundraiser(userName);
    }

    public void setFundraiser(Fundraiser fundraiser) {
        this.fundraiser = fundraiser;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    
    
    public Cause getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(Cause selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

    public List<Cause> getStatusList() {
        return querrySrv.getCauseList();
    }

    public void setStatusList(List<Cause> statusList) {
        this.statusList = statusList;
    } 

    public Cause getCause() {
        return cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Cause> getList() {
        return statusList;
    }

    public void setList(List<Cause> list) {
        this.statusList = list;
    }
    
    public String registerActivity(){
        try{
        regSrv.registerActivity(querrySrv.getFundraiser(userName), this.selectedStatus,this.activityName, 
                this.description,this.getActivityDate());
        }catch(Exception e){
            String message = "Something happened : " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, 
        new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return null;
        }
        return "/fundraiser/index";
    }
    
    public void valueChangeMethod(ValueChangeEvent e){
        //cause=(Cause)e.getNewValue();
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.querrySrv);
        hash = 73 * hash + Objects.hashCode(this.regSrv);
        hash = 73 * hash + Objects.hashCode(this.tranSrv);
        hash = 73 * hash + Objects.hashCode(this.userName);
        hash = 73 * hash + Objects.hashCode(this.fundraiser);
        hash = 73 * hash + Objects.hashCode(this.cause);
        hash = 73 * hash + Objects.hashCode(this.description);
        hash = 73 * hash + Objects.hashCode(this.selectedStatus);
        hash = 73 * hash + Objects.hashCode(this.statusList);
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
        final ActivityRegistrationBean other = (ActivityRegistrationBean) obj;
        if (!Objects.equals(this.querrySrv, other.querrySrv)) {
            return false;
        }
        if (!Objects.equals(this.regSrv, other.regSrv)) {
            return false;
        }
        if (!Objects.equals(this.tranSrv, other.tranSrv)) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.fundraiser, other.fundraiser)) {
            return false;
        }
        if (!Objects.equals(this.cause, other.cause)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.selectedStatus, other.selectedStatus)) {
            return false;
        }
        if (!Objects.equals(this.statusList, other.statusList)) {
            return false;
        }
        return true;
    }
    
    
}
