package aTPeopleCOS730;
import java.math.BigInteger;
import java.util.Date;

public interface ResearcherCategoryAssociation {
    BigInteger getId();

    Date getEffectiveDate();

    ResearcherCategory getCategory();
}
