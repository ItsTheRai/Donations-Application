/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.entity;

import com.gp225.securityexercise2.entity.systemusers.Fundraiser;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raimonds Grismanausks
 */
@Entity
@XmlRootElement
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ACTIVITY_ID")
    private Long activityId;
    
    @Column(name="NAME")
    String name;
    
    @Column(name="DESCRIPTION")
    String description="";
    
    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name="ACTIVITY_DATE")
    Date startDate;
    
    public Activity(){   
    }

    public Activity(Fundraiser fundraiser, Cause cause, String name, String description,Date date) {
        this.description = description;
        startDate =date;
        this.fundraiser=fundraiser;
        this.cause=cause;
        this.name=name;
    }
    
    @ManyToOne(optional=true,fetch=FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "USER_NAME",referencedColumnName="USER_NAME")
    private Fundraiser fundraiser;
    
    
    @ManyToOne(optional=true,fetch=FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "CAUSE_ID", referencedColumnName="CAUSE_ID")
    Cause cause;

    @OneToMany(mappedBy = "activity")
    List<Donation> donation;
    
    public Long getActivityId() {
        return activityId;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Fundraiser getFundraiser() {
        return fundraiser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cause getCause() {
        return cause;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.activityId);
        hash = 83 * hash + Objects.hashCode(this.description);
        hash = 83 * hash + Objects.hashCode(this.startDate);
        hash = 83 * hash + Objects.hashCode(this.fundraiser);
        hash = 83 * hash + Objects.hashCode(this.cause);
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
        final Activity other = (Activity) obj;
        if (!Objects.equals(this.activityId, other.activityId)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.fundraiser, other.fundraiser)) {
            return false;
        }
        if (!Objects.equals(this.cause, other.cause)) {
            return false;
        }
        return true;
    }

    
    
    
}
