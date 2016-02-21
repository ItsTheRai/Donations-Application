/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raimonds Grismanausks
 */
@Entity
@XmlRootElement
public class Account implements Serializable {
     
    @Id
    @Column(name="ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @NotNull
    @Column(name="ACCOUNT_BALANCE")
    private float accountBalance;
    
    public Account(){
    }
    
    public Account(float money){
        accountBalance = money;
    }
    public Long getId() {
        return accountId;
    }
    
    public float getAccountBalance(){
        return accountBalance;
    }
    
    public void addFunds(float amount){
        accountBalance +=amount;
    }
    
    public void withdrawFunds(float amount){
        if(validateTransaction(amount)){
            accountBalance-=amount;
        }
    }
    
    public boolean validateTransaction(float withdrawAmount){
        return accountBalance>=withdrawAmount;  
    }
    
    public boolean validateAccount(){
        return this.accountId!=null;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.accountId);
        hash = 41 * hash + Float.floatToIntBits(this.accountBalance);
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
        final Account other = (Account) obj;
        if (!Objects.equals(this.accountId, other.accountId)) {
            return false;
        }
        if (Float.floatToIntBits(this.accountBalance) != Float.floatToIntBits(other.accountBalance)) {
            return false;
        }
        return true;
    }
}
