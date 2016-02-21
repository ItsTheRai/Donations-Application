/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.entity.systemusers;

import com.gp225.securityexercise2.entity.Account;
import com.gp225.securityexercise2.entity.Activity;
import com.gp225.securityexercise2.entity.Donation;
import com.gp225.securityexercise2.entity.systemusers.User;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raimonds Grismanausks
 */
@Entity
@Table(name="FUNDRAISER")
@XmlRootElement
public class Fundraiser extends User implements Serializable{

    @NotNull
    @Column(name="FIRST_NAME")
    String firstName;
    
    @NotNull
    @Column(name="LAST_NAME")
    String lastName;
    
    @Column(name="MIDDLE_NAME")
    String middleName;
    
    @Column(name="DESCRIPTION")
    String description;
    
    public Fundraiser (){    
    }

    public Fundraiser(String username,String userpassword,
            String firstName, String lastName, String middleName,String description, Account account) {
        super(username,userpassword);
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName=middleName;
        this.description = description;
        this.account = account;
        
    }
    
    
    @OneToOne(optional=false,cascade=CascadeType.PERSIST)
    @JoinColumn(name="ACCOUNT_ID")
    Account account;

    @OneToMany(mappedBy="fundraiser")
    List<Activity> activities;

    public List<Activity> getActivities() {
        return activities;
    }
    
    
    
    public String getFirstName() {
        return firstName;
    }

    public Account getAccount() {
        return account;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName(){
        return userName;
    }
    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getDescription() {
        return description;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.getUserName());
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.lastName);
        hash = 29 * hash + Objects.hashCode(this.middleName);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.account);
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
        final Fundraiser other = (Fundraiser) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.getUserName(), other.getUserName())) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.middleName, other.middleName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.account, other.account)) {
            return false;
        }
        return true;
    }

    
}
