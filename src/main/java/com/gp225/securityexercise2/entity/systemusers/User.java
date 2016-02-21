/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.entity.systemusers;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author Raimonds Grismanausks
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)//JOINED)
public abstract class User implements Serializable {
    @Id
    @Column(name="USER_NAME")
     String userName;
    
    @Column(name="USER_PASSWORD")
     String userPassword;
    
    public User(){
        
    }
    public User(String username,String password){
        this.userName = username;
        this.userPassword=password;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getUserpassword() {
        return userPassword;
    }

    public void setUserpassword(String userpassword) {
        this.userPassword = userpassword;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.userName);
        hash = 19 * hash + Objects.hashCode(this.userPassword);
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
        final User other = (User) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.userPassword, other.userPassword)) {
            return false;
        }
        return true;
    }
}
