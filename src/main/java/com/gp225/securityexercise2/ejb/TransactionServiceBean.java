/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp225.securityexercise2.ejb;

import com.gp225.securityexercise2.ejb.exceptions.InvalidAccountException;
import com.gp225.securityexercise2.ejb.exceptions.InsufficientFundException;
import com.gp225.securityexercise2.ejb.exceptions.PaymentException;
import com.gp225.securityexercise2.entity.Account;
import com.gp225.securityexercise2.ejb.exceptions.SameAccountException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.eclipse.persistence.internal.jpa.EntityManagerFactoryImpl;

/**
 *
 * @author Raimonds Grismanausks
 */
@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class TransactionServiceBean implements TransactionService, Serializable{
    @Resource
    private EJBContext context;
    @Resource
    UserTransaction userTransaction;//init transsaction
    @PersistenceContext
    EntityManager em;
    
    @Override
    @RolesAllowed("fundraisers")
    public void makeDonation(Account fundraiser, float amount, Account cause) throws InvalidAccountException,
            InsufficientFundException,PaymentException, SystemException,SameAccountException{
        EntityManagerFactoryImpl i;
        //in case someone has put a minus sign in front of the input, cahnge it
        amount = Math.abs(amount);
        
        userTransaction=context.getUserTransaction();
        try{
            //start transaction
         userTransaction.begin();
         confirmAccountDetail(fundraiser);//check if a valid account
         withdrawAmount(fundraiser,amount);//take money from account

         confirmAccountDetail(cause);//confirm if second account is valid 
         depositAmount(cause,amount);//put money in seccond account
         
         //persist changes
         em.merge(fundraiser);
         em.merge(cause);
         //commit changes
         userTransaction.commit();
      }catch (InvalidAccountException | InsufficientFundException | NotSupportedException exception){
         userTransaction.rollback();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TransactionServiceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //check is account is a valid account
    private void confirmAccountDetail(Account account) 
      throws InvalidAccountException {
        if(em.find(Account.class, account.getId())==null){
            throw new InvalidAccountException();
        }
   }
   @RolesAllowed("fundraisers")
   //take money from account
   public void withdrawAmount(Account account, float amount) throws InsufficientFundException {
       if(account.validateTransaction(amount)){
           account.withdrawFunds(amount);
       }
       else throw new InsufficientFundException();
   }
   
   @RolesAllowed("fundraisers")
   //put money in account
   public void depositAmount(Account account, float amount){
       account.addFunds(amount);
   }
}