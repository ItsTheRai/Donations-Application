/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.entity;

import com.gp225.securityexercise2.entity.systemusers.Charity;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raimonds Grismanausks
 */
@Entity
@XmlRootElement
public class Cause implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CAUSE_ID")
    private Long causeId;
    
    @NotNull
    @Column(name="CAUSE_NAME")
    String causeName;
    
    @Column(name ="DESCRIPTION")
    String description;
    
    @NotNull
    @ManyToOne(optional=false,fetch=FetchType.EAGER)
    @JoinColumn(name = "USER_NAME",referencedColumnName="USER_NAME")
    private Charity charity;
    
    @OneToMany(cascade=ALL, mappedBy="cause")
    List<Activity> activity;
    
    public Cause (){
    }

    public Cause(String causeName,String description, Charity charity) {
        this.causeName=causeName;
        this.description = description;
        this.charity = charity;
    }

    public Long getCauseId() {
        return causeId;
    }

    public String getDescription() {
        return description;
    }

    public Charity getCharity() {
        return charity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public String getCauseName() {
        return causeName;
    }

    public void setCauseName(String causeName) {
        this.causeName = causeName;
    }

    @XmlTransient
    public List<Activity> getActivity() {
        return activity;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.causeId);
        hash = 37 * hash + Objects.hashCode(this.causeName);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.charity);
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
        final Cause other = (Cause) obj;
        if (!Objects.equals(this.causeId, other.causeId)) {
            return false;
        }
        if (!Objects.equals(this.causeName, other.causeName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.charity, other.charity)) {
            return false;
        }
        return true;
    }
}
