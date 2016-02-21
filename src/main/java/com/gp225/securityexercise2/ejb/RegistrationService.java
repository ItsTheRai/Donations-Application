/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.ejb;

import com.gp225.securityexercise2.entity.Account;
import com.gp225.securityexercise2.entity.Activity;
import com.gp225.securityexercise2.entity.Cause;
import com.gp225.securityexercise2.entity.systemusers.Charity;
import com.gp225.securityexercise2.entity.systemusers.Fundraiser;
import com.gp225.securityexercise2.ejb.exceptions.DuplicateUsernameException;
import com.gp225.securityexercise2.ejb.exceptions.InvalidEmailException;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Raimonds Grismanausks
 */
@Local
public interface RegistrationService {
    public void registerFundraiser(String username, String userpassword, 
            String name, String surname,String middleName,String description) throws InvalidEmailException,DuplicateUsernameException;
    public void registerCharity(String username,String userpassword, String uniqueName,
            String name, String description, String houseNumber,String houseName,
            String streetName,String postCode,Date dateCreated) throws InvalidEmailException,DuplicateUsernameException;
    public void registerAdmin(String name, String password) throws DuplicateUsernameException;
    public void registerCause(String causeName, String causeDescription,Charity charity);
    public void registerDonation(float amount,Fundraiser fundraiser, Activity activity);
    public void registerActivity(Fundraiser fundraiser,Cause cause,String causeName,String description,Date date);
}