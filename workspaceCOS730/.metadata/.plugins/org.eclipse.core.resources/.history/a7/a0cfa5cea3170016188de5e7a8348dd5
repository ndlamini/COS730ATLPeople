package ATPeopleCOS730;
import java.util.Date;

import aTPeopleCOS730.ResearchCategoryState;
import defaultImpl.*;
//aTPeopleCOS730
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testEquals()
    {
    	PersonImplementation pi;// = new PersonImplementation("Nkosikhona","Dlamini","dlamininkd@gmail.com",true);
    	
    	ResearcherCategoryAssociationImplementation rcai;//takes researcherCatergory 
    	ResearcherCategoryImplementation rci; //takes researchCategoryState 
    	ResearchCategoryStateImplementation rcs; // takes date and float.
    	Date mydate = new Date ();
    	
    	GroupImplementation gi;
    	try{
    		
    		rcs = new ResearchCategoryStateImplementation(mydate,0.4f);
    		//rcs they did not implement ResearchCatergoryState interface hence object Re 
    		rci = new ResearcherCategoryImplementation(rcs);
    		rcai =new ResearcherCategoryAssociationImplementation(rci);
    		gi = new GroupImplementation("Computer Science Research Group");
    		pi = new PersonImplementation("Nkosikhona","Dlamini","dlamininkd@gmail.com",true);
    		gi.addMember(pi);
    		gi.getMembers();
    		if(pi.isAdmin())
    		{
    			System.out.println("This guy is admin");
    			System.out.println("Test succeded");
    		}
    		Date otherDate =rcai.getEffectiveDate();
    		
    		//System.out.println(assertEquals(mydate,otherDate));   
    ///assertNot(mydate,otherDate );
    	}
    	catch(Exception ex)
    	{
    		System.out.println("Exception thrown " +ex.getMessage());
    	}
    
    }
  
    
}
