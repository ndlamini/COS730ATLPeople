package ATPeopleCOS730;

import aTPeopleCOS730.Group;
import aTPeopleCOS730.InvalidEmailException;
import aTPeopleCOS730.Person;
import aTPeopleCOS730.ResearchCategoryState;
import aTPeopleCOS730.ResearchGroupAssociationType;
import aTPeopleCOS730.ResearcherCategory;
import defaultImpl.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

//aTPeopleCOS730
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GroupImplementationTest

{
	
	private ResearchCategoryState researchCategoryState,researchCategoryState1;
	private  ResearchGroupAssociationImplementation regroupAsso1,regroupAsso2;
	private PersonImplementation admin,notAdmin,p_noValidEmail,researcher1,student,researcher2,leaderGroup1,leaderGroup2;
	private GroupImplementation group1, group2 ,group3;
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
		group3 = new GroupImplementation("SAEG");
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
		
		group1.addMember(student);
		group1.addMember(leaderGroup1);
		group1.addMember(researcher1);

		
		group3.addMember(admin);
		group3.addMember(notAdmin);
		group2.addMember(researcher2);
		group2.addMember(notAdmin);
		group2.addMember(leaderGroup2);
		
		group1.activateGroup();
		group2.activateGroup();
		
		group2.removeMember(notAdmin);
		group1.memberQuits(researcher1);
		group3.suspendGroup();
		
		
		
	}
	@Test
	public void testGroupMethods()
	{
		assertNotNull("Group class instatianed was null",group1);
		assertEquals("Group name returned by method not equal to expected group name","ICSA",group1.getName());
		assertTrue("Member not successfully added into group",group1.isMember(student));
		assertTrue("Group method activate failed",group1.getGroupState());
		assertFalse("Member remove unsuccesful to remove",group2.isMember(notAdmin));
		assertNotSame("Two different groups created are assumed to be the same",group1,group2);	
		assertNotNull("All group members were not added successfully list returned is null",group2.getMembers());
		assertFalse("Member is assumed to be of a group it was not added to ",group1.isMember(leaderGroup2));
		//assertFalse("The member who quit is still a member of this group",group1.isMember(researcher1));
		
		
	}
	@Test
	public void testSuspendedException()
	{
		try
		{
			group3.memberQuits(notAdmin);
			fail("Member cannot quit from a suspended group, an exception must be thrown");
		}
		catch(Exception e)
		{
			
		}
		
	}
	

}
