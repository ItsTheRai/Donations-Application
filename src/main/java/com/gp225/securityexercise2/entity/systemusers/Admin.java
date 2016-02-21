/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.entity.systemusers;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;

/**
 *
 * @author Raimonds Grismanausks
 */
@Entity
public class Admin extends User implements Serializable{
    public Admin(){
    }
    
    public Admin(String username,String password){
        super(username,password);
    }

    public String getUserName() {
        return userName;
    }
    
    @Override
    public int hashCode() {
        int hash = 21;
        hash = 21 * hash + Objects.hashCode(this.getUserName());
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
        final Admin other = (Admin) obj;
        if (!Objects.equals(this.getUserName(), other.getUserName())) {
            return false;
        }
        return true;
    }
}
