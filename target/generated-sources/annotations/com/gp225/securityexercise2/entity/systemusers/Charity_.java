package com.gp225.securityexercise2.entity.systemusers;

import com.gp225.securityexercise2.entity.Account;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-17T15:09:51")
@StaticMetamodel(Charity.class)
public class Charity_ extends User_ {

    public static volatile SingularAttribute<Charity, String> HouseNo;
    public static volatile SingularAttribute<Charity, String> houseName;
    public static volatile SingularAttribute<Charity, String> streetName;
    public static volatile SingularAttribute<Charity, Date> dateCreated;
    public static volatile SingularAttribute<Charity, String> name;
    public static volatile SingularAttribute<Charity, String> description;
    public static volatile SingularAttribute<Charity, String> charityName;
    public static volatile SingularAttribute<Charity, String> postCode;
    public static volatile SingularAttribute<Charity, Account> account;

}