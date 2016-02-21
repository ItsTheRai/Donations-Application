/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.ejb;

import com.gp225.securityexercise2.ejb.exceptions.InvalidAccountException;
import com.gp225.securityexercise2.ejb.exceptions.InsufficientFundException;
import com.gp225.securityexercise2.ejb.exceptions.PaymentException;
import javax.transaction.SystemException;
import com.gp225.securityexercise2.entity.Account;
import com.gp225.securityexercise2.ejb.exceptions.SameAccountException;
import javax.ejb.Local;

/**
 *
 * @author Raimonds Grismanausks
 */
@Local
public interface TransactionService {
    public void makeDonation(Account fundraiser,float amount,Account cause)throws InvalidAccountException,
            InsufficientFundException,PaymentException,SystemException,SameAccountException;
    //void confirmAccountDetail(Fundraiser account) throws InvalidAccountException;
    //void withdrawAmount() throws InsufficientFundException;
    //void depositAmount() throws PaymentException;
}
