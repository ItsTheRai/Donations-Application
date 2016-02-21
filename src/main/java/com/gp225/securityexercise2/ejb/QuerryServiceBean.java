/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.ejb;

import com.gp225.securityexercise2.entity.Activity;
import com.gp225.securityexercise2.entity.ActivityDonationObject;
import com.gp225.securityexercise2.entity.Cause;
import com.gp225.securityexercise2.entity.CauseDonationObject;
import com.gp225.securityexercise2.entity.Donation;
import com.gp225.securityexercise2.entity.SystemUser;
import com.gp225.securityexercise2.entity.systemusers.Charity;
import com.gp225.securityexercise2.entity.systemusers.Fundraiser;
import com.gp225.securityexercise2.entity.systemusers.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Raimonds Grismanausks
 */
@Stateless
@DeclareRoles({"charities","fundraisers","admins"})
//all querries are run through here
public class QuerryServiceBean implements QuerryService, Serializable{

    @PersistenceContext
    EntityManager em;//init entity manager


    @Override
    @RolesAllowed({"charities","admins"})
    //get a list of activities that belond to the cause causeId
    public List<Activity> getCauseActivityList(Long causeId) {
        Cause c=em.find(Cause.class, causeId); //find cause object
        List<Activity> activities;
        Query query= em.createQuery(
        "SELECT a FROM Activity a where a.cause = ?1");
        query.setParameter("1",c);
        activities = query.getResultList();//return a list of cause objects
    return activities;
    }
    
    @Override
    //return the charity object with the id username
    public Charity getCharity(String username) {
        Charity charity = em.find(Charity.class, username);//find it
        return charity;
    }
    
    @Override
    //return the fundraiser object with the in username
    public Fundraiser getFundraiser(String username) {
        Fundraiser fundraiser = em.find(Fundraiser.class, username);
        return fundraiser;
    }

    @Override
    @RolesAllowed({"charities","admins"})
    //return a cause object list that belongs to the charity with ID charity
    public List<Cause> getCharityCauseList(String userName) {
        Query query = em.createQuery(
            "SELECT c FROM Cause c where c.charity.userName like :userName");
        query.setParameter("userName",userName);
        List<Cause> causes =(List<Cause>)query.getResultList();//get results
        return causes;
    }
    
    @RolesAllowed({"charities","admins"})
    //return an integer that represents the total money donated to the cause with ID causeName
    public int getTotalDonationAmountForCause(String causeName){
        Query query = em.createQuery(
                "SELECT sum(d.donationAmount) s from Donation d where "
                        + "d.activity.cause.causeName like :causeName");
        query.setParameter("causeName", causeName);
        int total =(int)query.getSingleResult();
        return total;
    }

    @Override
    @RolesAllowed({"charities","admins","fundraisers"})
    //get a list of all donation objects that belong to activity with ID activityId
    public List<Donation> getActivityDonationList(Long activityId) {
        Activity activity =em.find(Activity.class, activityId);
    List<Donation> donations;
        Query query= em.createQuery(
        "SELECT a FROM Donation a where a.activity = ?1");
        query.setParameter("1",activity);
            donations = query.getResultList();
    return donations;
    }

    @Override
    @RolesAllowed({"fundraisers","admins"})
    //returns the list of all activity objects that belong to the user with ID userName
    public List<Activity> getFundraiserActivityList(String userName) {
        Fundraiser fundraiser = em.find(Fundraiser.class, userName);//get fundraiser object
        //return object
        List<Activity> activities;
        Query query= em.createQuery(
        "SELECT c FROM Activity c where c.fundraiser = ?1");
        query.setParameter("1",fundraiser);
        ///get results list
        activities = (List<Activity>)query.getResultList();
    return activities;
    }

    @Override
    @RolesAllowed({"charities","admins"})
    //get a mixed list of cause objects with the total amount of money donated to that cause
    public List<CauseDonationObject> getCauseDonationList(String userName) {
        Charity charity = this.getCharity(userName);//charity object with ID username
        //define custom return object
        List<CauseDonationObject> activities;
        List<Cause>causeList=this.getCharityCauseList(userName);
        Query query= em.createQuery(
        "SELECT NEW com.gp225.securityexercise2.entity.CauseDonationObject"
                + "(c.causeId,c.causeName,c.description,sum(d.donationAmount))"
                + " FROM Cause c  JOIN c.activity a JOIN a.donation d WHERE d.activity.cause.charity = ?1 group by c.causeId,c.causeName,c.description");
        query.setParameter("1",charity);
            activities = query.getResultList();
            //cant find a way to include all causes event the one without any donations so
            //do a workaround, add all not mentioned causes manually
            //add objects now if not already there
            for(Cause c:causeList){
                    boolean flag = true;
                    for (CauseDonationObject cdo:activities){
                        if(cdo.getCauseName().equals(c.getCauseName())&&cdo.getCauseDescription().equals(c.getDescription())&&
                                charity.equals(c.getCharity())){
                            flag=false;
                            break;
                        }
                    }
                if(flag){
                    activities.add(new CauseDonationObject(c.getCauseId(),c.getCauseName(),c.getDescription(),0));
                }
            }    
    return activities;
    }

    @Override
    @RolesAllowed({"fundraisers","admins","charities"})
    //get a list of all cause objects
    public List<Cause> getCauseList() {
        Query query = em.createQuery(
            "SELECT c FROM Cause c");
        List<Cause> causes =(List<Cause>)query.getResultList();
        if (causes.isEmpty()) {
            System.out.println("No persons found . ");
        } else {
        }
        return causes;
    }

    @Override
    //returns a list of mixed object with activity data for the user fundraiser with the sum of donations
    @RolesAllowed({"fundraisers","admins","charities"})
    public List<ActivityDonationObject> getActivityDonationList(Fundraiser fundraiser) {
        List<ActivityDonationObject> activities;
        List<Activity>activityList = getFundraiserActivityList(fundraiser.getUserName());
        Query query= em.createQuery(
               // "select a from Cause c join c.activity.donation d where d.activity.cause."
        "SELECT NEW com.gp225.securityexercise2.entity.ActivityDonationObject"
                + "(a.activityId, a.name, a.startDate, a.description,sum(d.donationAmount))"
                + " FROM Activity a JOIN a.donation d WHERE d.activity.fundraiser = "
                + "?1 group by a.activityId,a.name,a.startDate,a.description");
        query.setParameter("1",fundraiser);
            activities = query.getResultList();
            //cant find a way to include all causes event the one without any donations so
            //do a workaround, add all not mentioned causes manually
            //add objects now if not already there
            for(Activity c:activityList){
                    boolean flag = true;
                    for (ActivityDonationObject cdo:activities){
                        if(cdo.getActivityId()==c.getActivityId()&& cdo.getAcivityName().equals(c.getName())&& cdo.getDescription().equals(c.getDescription())&&
                        cdo.getStartDate().equals(c.getStartDate()) && fundraiser.equals(c.getFundraiser())){
                            flag=false;
                            break;
                        }
                    }
                if(flag){
                    activities.add(new ActivityDonationObject(c.getActivityId(),c.getName(),c.getStartDate(),c.getDescription(),0.0));
                }
            }
        return activities;
    }
    
    @Override
    @PermitAll
   //checks is string email is alread in the username database returns true if a duplicate
    public boolean isDuplicateEmail(String email){
        //look for user with name email in database
        Query query= em.createQuery("select a from SystemUser a where a.username like :email");
        query.setParameter("email", email);
        List<SystemUser> list;
        //have to catch exeption that is thrown if no entries
        try{
        list = (List<SystemUser>) query.getResultList();
        }catch(Exception e){
            return false;
        }
        return !list.isEmpty();
    }

    @Override
    @RolesAllowed({"fundraisers","admins","charities"})
    //get a list of all activity objects 
    public List<Activity> getActivityList() {
        List<Activity> list;
        Query query= em.createQuery("select a from Activity a");
        list = query.getResultList();
        return list;
    }

    @Override
    @RolesAllowed({"admins"})
    //get a list of alll fundraisers
    public List<Fundraiser> getFundraisers() {
        List<Fundraiser> list;
        Query query= em.createQuery("select a from Fundraiser a");
        list = query.getResultList();
        return list;
    }

    @Override
    @RolesAllowed({"admins"})
    //get a list of all charities
    public List<Charity> getCharities() {
        List<Charity> list;
        Query query= em.createQuery("select a from Charity a");
        list = query.getResultList();
        return list;
    }
}