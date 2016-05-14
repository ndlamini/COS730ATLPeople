package ATPeopleCOS730;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner 
{

		  public static void main(String[] args) {
		    Result result = JUnitCore.runClasses(PersonImplementationTest.class,GroupImplementationTest.class,ResearchCategoryStateImplementationTest.class,ResearcherCategoryAssociationImplementationTest.class,ResearcherCategoryImplementationTest.class,ResearchGroupAssociationImplementationTest.class);
		    for (Failure failure : result.getFailures()) {
		      System.out.println(failure.toString());
		    
		  }
		} 
	
}
