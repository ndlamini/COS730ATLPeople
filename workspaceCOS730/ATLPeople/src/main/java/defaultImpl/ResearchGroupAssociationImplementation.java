/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultImpl;

import javax.persistence.*;

import aTPeopleCOS730.DateInvalidException;
import aTPeopleCOS730.Group;
import aTPeopleCOS730.ResearchGroupAssociation;
import aTPeopleCOS730.ResearchGroupAssociationType;

import java.math.BigInteger;
import java.util.Date;

/**
 * This class represents an association between a PersonImplementation has with a GroupImplementation object
 * and defines its type
 */
@javax.persistence.Entity
public class ResearchGroupAssociationImplementation implements ResearchGroupAssociation {
    /**
    *  Defines the no-args constructor for a PersonImplementation
    *  Protected, as it should not be used
    *  (especially not outside of the people package)
    */
    protected ResearchGroupAssociationImplementation(){}
    
    
    /**
    * Defines the interface of the standard constructor for
    * a basic ResearchGroupAssociationImplementation. Assumes that starting date is NOW.
    * Also assumes association has not ended
    * @param    group   The GroupImplementation in the association
    * @param    type    The type of association this is    
    */
    public ResearchGroupAssociationImplementation(Group group, ResearchGroupAssociationType type)
    {
        this.group = group;
        this.type = type;
        this.startDate = (new Date());        
    }
    
    /**
    * Defines the interface for a constructor for a ResearchGroupAssociationImplementation wherein
    * a start date has been specified. Assumes association has not ended.
    * @param    group   The GroupImplementation in the association
    * @param    type    The type of association this is
    * @param    startDate   The date upon which the association started
    * @throws   com.codinginfinity.research.people.DateInvalidException    Throws an exception if the date provided for
    *  a start date is in the future
    */
    public ResearchGroupAssociationImplementation(Group group, ResearchGroupAssociationType type, Date startDate)
            throws DateInvalidException
    {
        this.group = group;
        if(startDate.after(new Date())) 
            throw new DateInvalidException("Date "+startDate+" is in the future,"
                    + " but a research group association must start in the past");
        this.startDate = startDate;
    }
    
    /**
    * Defines the interface for a constructor for a ResearchGroupAssociationImplementation wherein
    * a start date has been specified as well as an end date (thus we know this association is over)
    * @param    group   The GroupImplementation in the association
    * @param    type    The type of association this is
    * @param    startDate   The date upon which the association started
    * @param    endDate The date upon which this association ended
    * @throws   com.codinginfinity.research.people.DateInvalidException    Throws an exception if the date provided for
    *  a start date is in the future or if provided end date is in the past
    */
    public ResearchGroupAssociationImplementation(Group group, ResearchGroupAssociationType type, Date startDate, Date endDate)
            throws DateInvalidException
    {
        this.group = group;
        if(startDate.after(new Date()))
            throw new DateInvalidException("Date "+startDate+" is in the future,"
                    + " but a research group association must start in the past");
        this.startDate = startDate;
        
        
        if(endDate.after(new Date()))
            throw new DateInvalidException("Date "+endDate+" is in the future,"
            +" but a research group association cannot have an end date if that"
            +" date has yet to come to pass");
        this.endDate = endDate;
    }


    public BigInteger getId() {
        return this.id;
    }

    /**
     * Returns the GroupImplementation in the association
     * @return  The GroupImplementation in the association
     */
    public Group getGroup() { return this.group; }


    /**
     * Returns the date upon which the association started
     * @return  The Start date for the association
     */
    public Date getStartDate() { return this.startDate; }
    
    /**
     * Returns the date upon which the association ended
     * @return  The End date of the association
     */
    public Date getEndDate() { return this.endDate; }

    
    public void setStartDate(Date date)
    {
        this.startDate = date;
    }
    
    public void setEndDate(Date date)
    {
        this.endDate = date;
    }
    /**
     * Returns the type of this association
     * @return The type of association this is
     */
    public ResearchGroupAssociationType getType() { return this.type; }
    
    /*
    * Member variables
    */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private BigInteger id;
    
    /**
     * The GroupImplementation in the association
     */
    @OneToOne(targetEntity = GroupImplementation.class)
    private Group group;
    
    /**
     * The date upon which the association started
     */
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    /**
     * The date upon which the association ended
     */
    @Temporal(TemporalType.DATE)
    Date endDate;
    
    /**
     * The type of the association
     */
    @Basic
    ResearchGroupAssociationType type;
}
