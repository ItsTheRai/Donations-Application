package com.gp225.securityexercise2.entity;

import com.gp225.securityexercise2.entity.Activity;
import com.gp225.securityexercise2.entity.systemusers.Charity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-17T15:09:51")
@StaticMetamodel(Cause.class)
public class Cause_ { 

    public static volatile SingularAttribute<Cause, Long> causeId;
    public static volatile ListAttribute<Cause, Activity> activity;
    public static volatile SingularAttribute<Cause, Charity> charity;
    public static volatile SingularAttribute<Cause, String> causeName;
    public static volatile SingularAttribute<Cause, String> description;

}