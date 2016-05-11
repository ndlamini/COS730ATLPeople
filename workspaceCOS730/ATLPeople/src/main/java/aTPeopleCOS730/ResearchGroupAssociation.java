package aTPeopleCOS730;

import java.math.BigInteger;
import javax.persistence.*;
import java.util.Date;

public interface ResearchGroupAssociation
{
    BigInteger getId();
    
    Group getGroup();

    Date getStartDate();
    
    Date getEndDate();

    ResearchGroupAssociationType getType();
    
}
