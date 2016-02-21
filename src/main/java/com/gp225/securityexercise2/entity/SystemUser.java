package com.gp225.securityexercise2.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author gp225
 */
@Entity
public class SystemUser implements Serializable {
    
    @Id
    private String username;
    private String userpassword;

    public SystemUser() {
    }

    public SystemUser(String username, String groupName) {
        this.username = username;
        this.userpassword = groupName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupName() {
        return userpassword;
    }

    public void setGroupName(String groupName) {
        this.userpassword = groupName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.username);
        hash = 47 * hash + Objects.hashCode(this.userpassword);
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
        final SystemUser other = (SystemUser) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.userpassword, other.userpassword)) {
            return false;
        }
        return true;
    }

    

}
