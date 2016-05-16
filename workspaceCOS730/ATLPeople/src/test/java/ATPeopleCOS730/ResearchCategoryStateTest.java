
package ATPeopleCOS730;
import aTPeopleCOS730.ResearchCategoryState;

import defaultImpl.*;

import org.junit.Assert.*;

import java.util.Date;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
 * Created by Mothusi Masibi
 */
public class ResearchCategoryStateTest {

    private ResearchCategoryState categoryState1, categoryState2, categoryState3;
    private ResearcherCategory category1, category2, category3;
    private static EntityManagerFactory factory;
    private EntityManager entityManager;
    private final String PERSISTENCE_UNIT_NAME = "RSSPeoplePersistence";

    private void creatingResearchCategoryState() throws DateInvalidException {
        categoryState1 = new ResearchCategoryStateImplementation(new Date(), 100);
        categoryState2 = new ResearchCategoryStateImplementation(new Date(), 200);
        categoryState3 = new ResearchCategoryStateImplementation(new Date(), 300);
    }

    @Before
    public void initTestFixture() throws Exception {
        // Get the entity manager for the tests.
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = factory.createEntityManager();
    }

    @Test
    public void persistResearchCategoryState() throws DateInvalidException {

        entityManager.getTransaction().begin();
        creatingResearchCategoryState();

        // assert that the person Ids are still null (default values)
        Assert.assertTrue(categoryState1.getId() == null);
        Assert.assertTrue(categoryState2.getId() == null);
        Assert.assertTrue(categoryState3.getId() == null);

        entityManager.persist(categoryState1);
        entityManager.persist(categoryState2);
        entityManager.persist(categoryState3);
        entityManager.getTransaction().commit();

        Assert.assertTrue(entityManager.contains(categoryState1));
        Assert.assertTrue(entityManager.contains(categoryState2));
        Assert.assertTrue(entityManager.contains(categoryState3));

        // assert that the person Ids have now been generated
        Assert.assertTrue(categoryState1.getId().longValue() > 0);
        Assert.assertTrue(categoryState1.getId().longValue() > 0);
        Assert.assertTrue(categoryState1.getId().longValue() > 0);
    }
    
     private void creatingResearchCategory() throws DateInvalidException {
         
        categoryState1 = new ResearchCategoryStateImplementation(new Date(), 100);
        category1 = new ResearcherCategoryImplementation(categoryState1);
        categoryState2 = new ResearchCategoryStateImplementation(new Date(), 200);
        category2 = new ResearcherCategoryImplementation(categoryState2);
        categoryState3 = new ResearchCategoryStateImplementation(new Date(), 300);
        category3 = new ResearcherCategoryImplementation(categoryState3);
    }
    
     @Test
    public void persistResearchCategory() throws DateInvalidException {
    
        entityManager.getTransaction().begin();
        creatingResearchCategory();

        // assert that the person Ids are still null (default values)
        Assert.assertTrue(category1.getId() == null);
        Assert.assertTrue(category2.getId() == null);
        Assert.assertTrue(category3.getId() == null);

        entityManager.persist(category1);
        entityManager.persist(category2);
        entityManager.persist(category3);
        entityManager.getTransaction().commit();

        Assert.assertTrue(entityManager.contains(category1));
        Assert.assertTrue(entityManager.contains(category2));
        Assert.assertTrue(entityManager.contains(category3));

        // assert that the person Ids have now been generated
        Assert.assertTrue(category1.getId().longValue() > 0);
        Assert.assertTrue(category2.getId().longValue() > 0);
        Assert.assertTrue(category3.getId().longValue() > 0);        
    }

    @After
    public void closeTestFixture() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

}
