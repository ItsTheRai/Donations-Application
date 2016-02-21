package com.gp225.securityexercise2.entity.systemusers;

import com.gp225.securityexercise2.entity.Account;
import com.gp225.securityexercise2.entity.Activity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-17T15:09:51")
@StaticMetamodel(Fundraiser.class)
public class Fundraiser_ extends User_ {

    public static volatile SingularAttribute<Fundraiser, String> firstName;
    public static volatile SingularAttribute<Fundraiser, String> lastName;
    public static volatile ListAttribute<Fundraiser, Activity> activities;
    public static volatile SingularAttribute<Fundraiser, String> description;
    public static volatile SingularAttribute<Fundraiser, String> middleName;
    public static volatile SingularAttribute<Fundraiser, Account> account;

}