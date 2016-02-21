package com.gp225.securityexercise2.jsf;

import com.gp225.securityexercise2.ejb.exceptions.InvalidEmailException;
import com.gp225.securityexercise2.ejb.exceptions.DuplicateUsernameException;
import com.gp225.securityexercise2.ejb.RegistrationService;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author parisis
 */

@ManagedBean
@RequestScoped
public class RegistrationBean implements Serializable{

    @EJB
    RegistrationService usrSrv;
    
    String username;
    String userpassword;
    String fundraiserName;
    String fundraiserSurname;
    String fundraiserMiddleName;
    
    String fundraiserDescription;
    
    String charityName;
    String charityDescription;
    String charityHouseNo;
    String charityHouseName;
    String charityStreetName;
    String charityPostCode;
    String charityUniqueName;
    
    public RegistrationBean() {

    }

    //call the injected EJB
    public String registerFundraiser() throws DuplicateUsernameException {
        try{
        usrSrv.registerFundraiser(this.username, this.userpassword, this.fundraiserName, this.fundraiserSurname,
                this.fundraiserMiddleName,this.fundraiserDescription);
        }catch(InvalidEmailException e){
            String message = "Entered email address is not a valid email address";
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return null;
        }catch(DuplicateUsernameException e){
            String message = "Entered email address is already in use";
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return null;
        }//catch(Exception e){
        //    return null;
       // }
        
        return "index";
    }
    
    public String registerCharity() throws DuplicateUsernameException{
        try{
        usrSrv.registerCharity(this.username, this.userpassword, this.charityUniqueName,
                this.charityName, this.charityDescription, this.charityHouseNo, this.charityHouseName, 
                this.charityStreetName, this.charityPostCode, new Date());
        }catch(InvalidEmailException e){
            String message = "Entered email address is not a valid email address";
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return null;
        }
        catch(DuplicateUsernameException e){
            String message = "Entered email address is already in use";
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return null;
        }//catch(Exception e){
         //   return null;
        //}
        return "index";
    }
    
    public String registerAdmin() throws DuplicateUsernameException{
        try{
        usrSrv.registerAdmin(username, userpassword);
        
        }catch(DuplicateUsernameException e){
            String message = "Entered username is already in use";
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return null;
        }
        return "index";
    }

    public RegistrationService getUsrSrv() {
        return usrSrv;
    }

    
    public String getUsername() {
        return username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public String getFundraiserMiddleName() {
        return fundraiserMiddleName;
    }

    public void setFundraiserMiddleName(String fundraiserMiddleName) {
        this.fundraiserMiddleName = fundraiserMiddleName;
    }
    
    public String getFundraiserName() {
        return fundraiserName;
    }

    public String getFundraiserSurname() {
        return fundraiserSurname;
    }

    public String getFundraiserDescription() {
        return fundraiserDescription;
    }

    public String getCharityUniqueName() {
        return charityUniqueName;
    }

    
    public String getCharityName() {
        return charityName;
    }

    public String getCharityDescription() {
        return charityDescription;
    }

    public String getCharityHouseNo() {
        return charityHouseNo;
    }

    public String getCharityHouseName() {
        return charityHouseName;
    }

    public String getCharityStreetName() {
        return charityStreetName;
    }

    public String getCharityPostCode() {
        return charityPostCode;
    }

    public void setUsrSrv(RegistrationService usrSrv) {
        this.usrSrv = usrSrv;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public void setFundraiserName(String fundraiserName) {
        this.fundraiserName = fundraiserName;
    }

    public void setFundraiserSurname(String fundraiserSurname) {
        this.fundraiserSurname = fundraiserSurname;
    }

    public void setFundraiserDescription(String fundraiserDescription) {
        this.fundraiserDescription = fundraiserDescription;
    }

    public void setCharityUniqueName(String charityUniqueName) {
        this.charityUniqueName = charityUniqueName;
    }

    
    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    public void setCharityDescription(String charityDescription) {
        this.charityDescription = charityDescription;
    }

    public void setCharityHouseNo(String charityHouseNo) {
        this.charityHouseNo = charityHouseNo;
    }

    public void setCharityHouseName(String charityHouseName) {
        this.charityHouseName = charityHouseName;
    }

    public void setCharityStreetName(String charityStreetName) {
        this.charityStreetName = charityStreetName;
    }

    public void setCharityPostCode(String charityPostCode) {
        this.charityPostCode = charityPostCode;
    }
}