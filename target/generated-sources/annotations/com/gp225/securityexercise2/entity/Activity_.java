package com.gp225.securityexercise2.entity;

import com.gp225.securityexercise2.entity.Cause;
import com.gp225.securityexercise2.entity.Donation;
import com.gp225.securityexercise2.entity.systemusers.Fundraiser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-17T15:09:51")
@StaticMetamodel(Activity.class)
public class Activity_ { 

    public static volatile SingularAttribute<Activity, Fundraiser> fundraiser;
    public static volatile SingularAttribute<Activity, Long> activityId;
    public static volatile SingularAttribute<Activity, String> name;
    public static volatile SingularAttribute<Activity, String> description;
    public static volatile SingularAttribute<Activity, Cause> cause;
    public static volatile ListAttribute<Activity, Donation> donation;
    public static volatile SingularAttribute<Activity, Date> startDate;

}