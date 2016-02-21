/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.entity.systemusers;

import com.gp225.securityexercise2.entity.Account;
import com.gp225.securityexercise2.entity.systemusers.User;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raimonds Grismanausks
 */
@Entity
@Table(name="CHARITY")
@XmlRootElement
public class Charity extends User implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="CHARITY_ID")
//    private Long charity_Id;
    
    @NotNull
    @Column(name="CHARITY_NAME",unique=true)
    String charityName;
    
    @NotNull
    @Column(name="NAME")
    String name;
    
    @NotNull
    @Column(name="Description")
    String description;
    
    @Column(name="HouseNo")
    String HouseNo;
    
    @Column(name="HouseName")
    String houseName;
    
    @NotNull
    @Column(name="StreetName")
    String streetName;
    
    @NotNull
    @Column(name="POSTCODE")
    String postCode;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @Column(name="DateCreated")
    Date dateCreated;
    
    public Charity(){  
    }

    public Charity(String username, String password,
            String uniqueName,String name, String description, 
            String HouseNo, String houseName, String streetName, String postCode, 
            Account account,Date dateCreated) {
        super(username,password);
        this.charityName=uniqueName;
        this.name = name;
        this.description = description;
        this.HouseNo = HouseNo;
        this.houseName = houseName;
        this.streetName = streetName;
        this.postCode = postCode;
        this.account = account;
        this.dateCreated = dateCreated;
    }
    
    @OneToOne(optional=false,cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
    @JoinColumn(name="ACCOUNT_ID")
    Account account;

    
    
    public String getCharityName() {
        return charityName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHouseNo() {
        return HouseNo;
    }

    public String getHouseName() {
        return houseName;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getPostCode() {
        return postCode;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Account getAccount() {
        return account;
    }

    public String getUserName() {
        return userName;
    }

    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHouseNo(String HouseNo) {
        this.HouseNo = HouseNo;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.getUserName());
        hash = 59 * hash + Objects.hashCode(this.charityName);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.HouseNo);
        hash = 59 * hash + Objects.hashCode(this.houseName);
        hash = 59 * hash + Objects.hashCode(this.streetName);
        hash = 59 * hash + Objects.hashCode(this.postCode);
        hash = 59 * hash + Objects.hashCode(this.dateCreated);
        hash = 59 * hash + Objects.hashCode(this.account);
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
            final Charity other = (Charity) obj;
            if (!Objects.equals(this.getUserName(), other.getUserName())) {
                return false;
            }
        if (!Objects.equals(this.charityName, other.charityName)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.HouseNo, other.HouseNo)) {
            return false;
        }
        if (!Objects.equals(this.houseName, other.houseName)) {
            return false;
        }
        if (!Objects.equals(this.streetName, other.streetName)) {
            return false;
        }
        if (!Objects.equals(this.postCode, other.postCode)) {
            return false;
        }
        if (!Objects.equals(this.dateCreated, other.dateCreated)) {
            return false;
        }
        if (!Objects.equals(this.account, other.account)) {
            return false;
        }
        return true;
    }

    
    
    
}
