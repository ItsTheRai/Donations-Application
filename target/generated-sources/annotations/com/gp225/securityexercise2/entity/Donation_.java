package com.gp225.securityexercise2.entity;

import com.gp225.securityexercise2.entity.Activity;
import com.gp225.securityexercise2.entity.systemusers.Fundraiser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-17T15:09:51")
@StaticMetamodel(Donation.class)
public class Donation_ { 

    public static volatile SingularAttribute<Donation, Fundraiser> fundraiser;
    public static volatile SingularAttribute<Donation, Activity> activity;
    public static volatile SingularAttribute<Donation, Long> donationId;
    public static volatile SingularAttribute<Donation, Date> transactionDate;
    public static volatile SingularAttribute<Donation, Double> donationAmount;

}