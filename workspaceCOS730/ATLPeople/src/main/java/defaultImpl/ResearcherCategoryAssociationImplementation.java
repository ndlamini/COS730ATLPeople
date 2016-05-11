/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultImpl;

import aTPeopleCOS730.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigInteger;
import java.util.Date;

/**
 * This defines an association between a Reseacher (a type of person) and a
 * ResearcherCategoryImplementation
 */
@javax.persistence.Entity
public class ResearcherCategoryAssociationImplementation implements ResearcherCategoryAssociation {
    /**
    *  Defines the no-args constructor for a ResearchCategoryAssociation
    *  Protected, as it should not be used
    *  (especially not outside of the people package)
    */
    protected ResearcherCategoryAssociationImplementation() {}
    
    /**
     * Defines the standard constructor for a ResearchCategoryAssociation, which
     * assumes date to be NOW and is provided a ResearcherCategoryImplementation
     * @param category The researcher category belonging to this association
     */
    public ResearcherCategoryAssociationImplementation(ResearcherCategory category)
    {
        this.effectiveDate = (new Date());
        this.category = category;
    }
    
    
    /**
     * Defines a second constructor for a ResearchCategoryAssociation, which
     * is given a date and a ResearcherCategoryImplementation
     * @param   categoryID    The researcher category belonging to this association
     * @param   effectiveDate The effective date of this association
     * @throws com.codinginfinity.research.people.DateInvalidException Throws if a date in the future is given for a past event
     */
    public ResearcherCategoryAssociationImplementation(ResearcherCategory categoryID, Date effectiveDate)
            throws DateInvalidException
    {
        this.category = categoryID;
        
        if(effectiveDate.after(new Date()))
            throw new DateInvalidException("The date "+effectiveDate+" must be in the past"
            +" in order for it to be a valid date for a category association to become effective");
        this.effectiveDate = effectiveDate;

    }

    public BigInteger getId() {
        return this.id;
    }

    /**
     * Returns the effective date of this association
     * @return The effective date belonging to this association
     */
    public Date getEffectiveDate() { return this.effectiveDate; }
    
    /**
     * Returns the research category for this association
     * @return The research category belonging to this association
     */
    public ResearcherCategory getCategory() { return this.category; }
    /*
    * Member variables
    */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private BigInteger id;
    
    /**
     * The effective date of this saved state
     */
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;
    
    /**
     * The Researcher category belonging to this state
     */
    @OneToOne(targetEntity = ResearcherCategoryImplementation.class)
    private ResearcherCategory category;
}
