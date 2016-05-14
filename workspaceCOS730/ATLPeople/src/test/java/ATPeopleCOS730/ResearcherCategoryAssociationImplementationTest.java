package ATPeopleCOS730;
import aTPeopleCOS730.ResearchCategoryState;

import defaultImpl.*;

import static org.junit.Assert.*;

import java.util.Date;
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;

import org.junit.Before;
import org.junit.Test;

public class ResearcherCategoryAssociationImplementationTest 

{
	//need a researcherCatergory
	
	//researcherCatergory needs researchSatecatergory
	private ResearcherCategoryAssociationImplementation rcai1,rcai2;
	private ResearcherCategoryImplementation researchCatergory1,researchCatergory2;
	private ResearchCategoryState researchCategoryState,researchCategoryState1;
	
	Date myDate =new Date();
	
	@Before
	public void createResearcherAssociationImplementation() throws Exception
	{
		
		researchCategoryState1 = new ResearchCategoryStateImplementation(myDate,0.4f);
		researchCategoryState = new ResearchCategoryStateImplementation(myDate,0.9f);
		
		//regroupAsso1 = new ResearchGroupAssociationImplementation(researhgrpAssiaType.STUDENT,);
		//regroupAsso2 = new ResearchGroupAssociationImplementation();
		
		researchCatergory1 = new ResearcherCategoryImplementation(researchCategoryState);
		researchCatergory2 =new ResearcherCategoryImplementation(researchCategoryState1);
		
		
		rcai1 = new ResearcherCategoryAssociationImplementation(researchCatergory1);
		rcai2 =new ResearcherCategoryAssociationImplementation(researchCatergory2);	
	}
	@Test
	public void testResearcherAssociationImplementationMethods()
	{
		assertNotNull("Error : ResearchCategory association cannot be instatiated",rcai1);
		assertNotSame("Error : Different research catergories assumed to be the same",rcai1,rcai2);
		assertSame("Error    : The returned research catergory is expected to be the same ",researchCatergory1,rcai1.getCategory());
		assertNotNull("Error : The effective date returned by getEffectiveDate method is not susspoed to be null",rcai2.getEffectiveDate());	
	}
	
}
