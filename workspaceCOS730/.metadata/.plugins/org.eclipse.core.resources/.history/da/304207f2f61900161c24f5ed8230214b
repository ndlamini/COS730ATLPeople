package ATPeopleCOS730;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import aTPeopleCOS730.ResearchCategoryState;
import defaultImpl.ResearchCategoryStateImplementation;
import defaultImpl.ResearcherCategoryImplementation;

public class ResearcherCategoryImplementationTest {
	
	
	private ResearcherCategoryImplementation researchCatergory1,researchCatergory2;
	private ResearchCategoryState researchCategoryState,researchCategoryState1;
	
	Date myDate =new Date();
	
	@Before
	public void createResearcherCatergoryImplementation() throws Exception
	{
		
		researchCategoryState1 = new ResearchCategoryStateImplementation(myDate,0.4f);
		researchCategoryState = new ResearchCategoryStateImplementation(myDate,0.9f);
		
		//regroupAsso1 = new ResearchGroupAssociationImplementation(researhgrpAssiaType.STUDENT,);
		//regroupAsso2 = new ResearchGroupAssociationImplementation();
		
		researchCatergory1 = new ResearcherCategoryImplementation(researchCategoryState);
		researchCatergory2 =new ResearcherCategoryImplementation(researchCategoryState1);	
		
		//researchCatergory1.removeState(researchCategoryState);	
	}
	@Test
	public void testResearcherAssociationImplementationMethods()
	{
		assertNotNull("Error : Research catergory implementation fail to initialize",researchCatergory1);
		assertNotSame("Error: different research Catergory assumed to be the same ",researchCatergory1,researchCatergory2);
		assertNotNull("Error : returned null yet catergory states exists ",researchCatergory2.getStateIDs());
		assertEquals("Error state not successfully reomved from research catergory implemantation",researchCatergory2.getStateIDs().size(),researchCatergory1.getStateIDs().size());	
	}
	

}
