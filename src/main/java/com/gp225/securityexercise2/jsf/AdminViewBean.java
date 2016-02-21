/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.jsf;

import com.gp225.securityexercise2.ejb.QuerryService;
import com.gp225.securityexercise2.entity.systemusers.Charity;
import com.gp225.securityexercise2.entity.systemusers.Fundraiser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Raimonds Grismanausks
 */
@ManagedBean
public class AdminViewBean implements Serializable{
    @Inject
            QuerryService queryService;
    private List<Fundraiser> fundraisers;
    private List<Charity> charities;

    public AdminViewBean() {
    }

    public List<Fundraiser> getFundraisers() {
        return queryService.getFundraisers();
    }

    public List<Charity> getCharities() {
        return queryService.getCharities();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.fundraisers);
        hash = 89 * hash + Objects.hashCode(this.charities);
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
        final AdminViewBean other = (AdminViewBean) obj;
        if (!Objects.equals(this.fundraisers, other.fundraisers)) {
            return false;
        }
        if (!Objects.equals(this.charities, other.charities)) {
            return false;
        }
        return true;
    }
}
