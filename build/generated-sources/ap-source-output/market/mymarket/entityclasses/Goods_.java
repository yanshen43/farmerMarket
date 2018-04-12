package market.mymarket.entityclasses;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-14T15:13:41")
@StaticMetamodel(Goods.class)
public class Goods_ { 

    public static volatile SingularAttribute<Goods, String> image;
    public static volatile SingularAttribute<Goods, String> gName;
    public static volatile SingularAttribute<Goods, String> unit;
    public static volatile SingularAttribute<Goods, Integer> gid;
    public static volatile SingularAttribute<Goods, BigDecimal> price;
    public static volatile SingularAttribute<Goods, String> category;

}