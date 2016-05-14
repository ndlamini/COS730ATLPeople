package ATPeopleCOS730;
import static org.junit.Assert.*;

//package ATPeopleCOS730;
import java.util.Date;

import aTPeopleCOS730.Group;
import aTPeopleCOS730.InvalidEmailException;
import aTPeopleCOS730.Person;
import aTPeopleCOS730.ResearchCategoryState;
import aTPeopleCOS730.ResearchGroupAssociationType;
import aTPeopleCOS730.ResearcherCategory;
import defaultImpl.*;
//aTPeopleCOS730
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

 
public class PersonImplementationTest 
{
	private ResearchCategoryState researchCategoryState,researchCategoryState1;
	private  ResearchGroupAssociationImplementation regroupAsso1,regroupAsso2;
	private PersonImplementation admin,notAdmin,p_noValidEmail,researcher1,student,researcher2,leaderGroup1,leaderGroup2;
	private GroupImplementation group1, group2;
	private ResearchGroupAssociationType researhgrpAssiaType;
	private ResearcherCategoryImplementation researchCatergory1,researchCatergory2;
	
	Date myDate =new Date();
	
	@Before
	public void creatingAPerson() throws Exception
	{
		
		researchCategoryState1 = new ResearchCategoryStateImplementation(myDate,0.4f);
		researchCategoryState = new ResearchCategoryStateImplementation(myDate,0.9f);
		
		//regroupAsso1 = new ResearchGroupAssociationImplementation(researhgrpAssiaType.STUDENT,);
		//regroupAsso2 = new ResearchGroupAssociationImplementation();
		
		
		researchCatergory1 = new ResearcherCategoryImplementation(researchCategoryState);
		researchCatergory2 =new ResearcherCategoryImplementation(researchCategoryState1);
		admin = new PersonImplementation("Nkosikhona","Dlamini","dlamini@yahoo.com",true);
		notAdmin = new PersonImplementation("Tich","Mutopo","tmutop@gmail.com",false);
		researcher1 =new PersonImplementation("Lindiwe","Maake","lind@gmail.com",false);
		researcher2 =new PersonImplementation("Vreda","P","pvreda@gmail.com",false);
		student = new PersonImplementation("Mothusi","Masibi","mm@gmail.com",false);
		
		leaderGroup1 = new PersonImplementation("leaderGroup1Name","Group1LeaderSurname","group1leader@gmail.com",false);
		leaderGroup2 = new PersonImplementation("leaderGroup2Name","Group2LeaderSurname","group2leader@gmail.com",false);
		
		group1= new GroupImplementation("ICSA");
		group2= new GroupImplementation("CRIG");
		admin.addMemberToGroup(notAdmin, group1,researhgrpAssiaType.MEMBER);
		admin.addAuxiliaryEmails("adminauxiliaryemal@gmil.com");
		admin.addCategory(researchCatergory2);
		
		admin.addMemberToGroup(student, group1,researhgrpAssiaType.STUDENT);
		admin.addMemberToGroup(researcher1, group1,researhgrpAssiaType.MEMBER);
		admin.addMemberToGroup(researcher1, group1,researhgrpAssiaType.MEMBER);
		admin.addMemberToGroup(leaderGroup1, group1,researhgrpAssiaType.GROUPLEADER);
		
		admin.addMemberToGroup(researcher2, group2,researhgrpAssiaType.MEMBER);
		admin.addMemberToGroup(leaderGroup2, group2,researhgrpAssiaType.GROUPLEADER);
		admin.removeMemberFromGroup(student,group1);
		
		
	}
	@Test
	public void testPersonMethods()
	{
		assertNotNull("Person class instatianed was null",admin);
		assertEquals("GroupLeader1 email is not correspoding to expected eamil","group1leader@gmail.com",leaderGroup1.getPrimaryEmail());
		assertEquals("GroupLeader1 returned name is not correspoding to expected name","leaderGroup1Name",leaderGroup1.getFirstName());
		assertTrue("This person object is not Admin",admin.isAdmin());
		assertFalse("Staff is not Admin",notAdmin.isAdmin());
	}
	@Test
	public void testEmailExpetion()
	{
		try
		{
			p_noValidEmail= new PersonImplementation("Abubakar","Ossidigg","ab@..,gmail.com",false);
			
			fail("Invalid email exption not thrown");
		}
		catch(InvalidEmailException e)
		{
			assert(e.getMessage().equals(p_noValidEmail.getPrimaryEmail()+" is not a valid email address."));
		}
	}
	@Test
	public void testUnAuthorizedUserException()
	{
		try 
		{
			notAdmin.addMemberToGroup(researcher1, group1, researhgrpAssiaType.MEMBER);
			notAdmin.removeMemberFromGroup(researcher1, group1);
			notAdmin.activateGroup(group2);
			notAdmin.suspendGroup(group1);
			notAdmin.removeCategory(researchCatergory1);
			
			fail("Non admin or group leader person was able to do admin functions");
		}
		catch(Exception e)
		{
			assert(e.getMessage().equals("This is not leader of this group "));
			
		}
		
	}
	@Test 
	public void testNonMemberException()
	{
		try
		{
			leaderGroup1.addMemberToGroup(researcher2, group2, researhgrpAssiaType.MEMBER);
		
			fail("a non member was able tp add members to another group");
		}
		catch(Exception e)
		{
			assertNotNull(e.getMessage());
			
		}
			
	}
	@Test
	public  void adminFunctionsByAdmin()
	{
		try
		{
			admin.removeMemberFromGroup(student,group1);
			admin.activateGroup(group1);
			admin.removeCategory(researchCatergory1);
			admin.addCategory(researchCatergory2);
		}
		catch(Exception e)
		{
			assertNull(e.getMessage());
		}
		
	}
	
	
}
