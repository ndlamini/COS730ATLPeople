package ATPeopleCOS730;
import defaultImpl.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

public class ResearchCategoryStateImplementationTest
{
	
	private ResearchCategoryStateImplementation researchCategoryState,researchCategoryState1;
	
	Date myDate =new Date();
	
	@Before
	public void ResearchCategoryState() throws Exception
	{
		
		researchCategoryState1 = new ResearchCategoryStateImplementation(myDate,0.4f);
		researchCategoryState = new ResearchCategoryStateImplementation(myDate,0.9f);
	}
	@Test
	public void ResearchCategoryStateImplementationMethods()
	{
		assertNotNull("Could not instatiate RearchCategoryState",researchCategoryState1);
		assertNotSame("Different research catergory state assumed to be the same",researchCategoryState1,researchCategoryState);
		assertNotSame("Wrongly returns output targets that are not equal as equal",researchCategoryState1.getResearchOutputTarget(),researchCategoryState.getResearchOutputTarget());
		assertNotNull("Method to return date for research catergory failed to return the assigned date",researchCategoryState1.getEffectiveDate());
	}

}
