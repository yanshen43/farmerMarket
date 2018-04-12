package market.mymarket.entityclasses;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import market.mymarket.entityclasses.Photo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-14T15:13:41")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> lastName;
    public static volatile SingularAttribute<Customer, String> address2;
    public static volatile SingularAttribute<Customer, String> city;
    public static volatile SingularAttribute<Customer, Integer> securityQuestion;
    public static volatile SingularAttribute<Customer, String> securityAnswer;
    public static volatile SingularAttribute<Customer, String> address1;
    public static volatile CollectionAttribute<Customer, Photo> photoCollection;
    public static volatile SingularAttribute<Customer, String> ccSecurityCode;
    public static volatile SingularAttribute<Customer, String> ccExpires;
    public static volatile SingularAttribute<Customer, String> zipcode;
    public static volatile SingularAttribute<Customer, String> firstName;
    public static volatile SingularAttribute<Customer, String> password;
    public static volatile SingularAttribute<Customer, String> ccNumber;
    public static volatile SingularAttribute<Customer, String> middleName;
    public static volatile SingularAttribute<Customer, Integer> id;
    public static volatile SingularAttribute<Customer, String> state;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile SingularAttribute<Customer, String> username;

}