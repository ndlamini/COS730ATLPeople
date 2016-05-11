package defaultImpl;

import aTPeopleCOS730.DateInvalidException;
import aTPeopleCOS730.Group;
import aTPeopleCOS730.GroupSuspendedException;
import aTPeopleCOS730.NonMemberExeption;
import aTPeopleCOS730.UnauthorizedUserExeption;
import aTPeopleCOS730.InvalidEmailException;
import aTPeopleCOS730.Person;
import aTPeopleCOS730.ResearchCategoryState;
import aTPeopleCOS730.ResearchGroupAssociation;
import aTPeopleCOS730.ResearchGroupAssociationType;
import aTPeopleCOS730.ResearcherCategory;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * PersonImplementation type, an entity defined as being a record of a human being
 */
@Entity
@Table
public class PersonImplementation implements Person 
{
    //---------------------------CONSTRUCTORS---------------------------------------
    
    /**
    *  Defines the no-args constructor for a PersonImplementation
    *  Protected, as it should not be used
    *  (especially not outside of the people package)
    */
    protected PersonImplementation() {}
    /**
    * Defines the interface of the standard constructor for
    * a basic PersonImplementation.
    * @param    firstName   The name(s) of the PersonImplementation
    * @param    surname     The surname of the PersonImplementation
    * @param    primaryEmail The primary email address of the PersonImplementation
    * @throws   InvalidEmailException Throws when given an invalid email format
    */
    public PersonImplementation(String firstName, String surname, String primaryEmail, boolean isAdmin) throws InvalidEmailException
    {
        this.categoryAssociations = new ArrayList<ResearcherCategoryAssociationImplementation>();
        this.groupAssociations = new ArrayList<ResearchGroupAssociation>();
        this.auxiliaryEmails = new ArrayList<String>();
        
        this.firstName = firstName;
        this.surname = surname;

        String EMAIL_REGEX = "^[-\\w_\\.+]*[-\\w_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(primaryEmail.matches(EMAIL_REGEX))
            this.primaryEmail = primaryEmail;
        else throw new InvalidEmailException(primaryEmail + " is not a valid email address.");

        this.primaryEmail = primaryEmail;
        this.userID = null;
        
        this.isAdmin = isAdmin;
    }
    
    /**
    * Defines the interface of a constructor for a PersonImplementation
    * who has a user object already defined
    * @param    firstName   The name(s) of the PersonImplementation
    * @param    surname     The surname of the PersonImplementation
    * @param    primaryEmail The primary email address of the PersonImplementation
    * @param    userID        The id of the user object associated with the person
    * @throws   InvalidEmailException Throws if the given email does not match standard email format
    */
    public PersonImplementation(String firstName, String surname, String primaryEmail, BigInteger userID, boolean isAdmin) throws InvalidEmailException
    {
        this.categoryAssociations = new ArrayList<ResearcherCategoryAssociationImplementation>();
        this.groupAssociations = new ArrayList<ResearchGroupAssociation>();
        this.auxiliaryEmails = new ArrayList<String>();

        this.firstName = firstName;
        this.surname = surname;

        String EMAIL_REGEX = "^[-\\w_\\.+]*[-\\w_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(primaryEmail.matches(EMAIL_REGEX))
            this.primaryEmail = primaryEmail;
        else throw new InvalidEmailException(primaryEmail + " is not a valid email address.");

        this.primaryEmail = primaryEmail;
        this.userID = userID;
        this.isAdmin = isAdmin;
        
    }
    
    //------------------------------------------------------------------
    
    //-------------------------------ADMIN USER STUFF------------------------------------------------
        
        
        public void addResearcherCategory(ResearchCategoryState researchState)
        {
            ResearcherCategoryImplementation rs = new ResearcherCategoryImplementation(researchState);
        }

        public void addResearcherCategoryState(ResearcherCategory researcherCategory, ResearchCategoryState state) 
                throws UnauthorizedUserExeption
        {
            if(this.isAdmin)
                researcherCategory.addState(state);
            else
                throw new UnauthorizedUserExeption("This user is not an admin");
        }
        
        public void removeResearcherCategoryState(ResearcherCategory researcherCategory, ResearchCategoryState state) 
                throws UnauthorizedUserExeption
        {
            if(this.isAdmin)
                researcherCategory.removeState(state);
            else
                throw new UnauthorizedUserExeption("This user is not an admin");
        }
        
        
    public void addCategory(ResearcherCategory category)
    {
        categoryAssociations.add(new ResearcherCategoryAssociationImplementation(category));
    }
    
    public void addCategory(ResearcherCategory category, Date effectiveDate)
            throws DateInvalidException
    {
        categoryAssociations.add(new ResearcherCategoryAssociationImplementation(category, effectiveDate));
    }
    
    public void removeCategory(ResearcherCategoryImplementation category)
    {
        categoryAssociations.remove(category);
    }
   
    //-----------------------------------------------------------------------------------------------
    
    
    
    
    
    
    //---------------------------MEMBERSHIP MANAGEMENT(Admin and group leader)---------------------------------------
    //Admin/leader adds person to group
    public void addMemberToGroup(Person member, Group group, ResearchGroupAssociationType type)
            throws GroupSuspendedException, UnauthorizedUserExeption, NonMemberExeption
    {
        int index = -1;
        ResearchGroupAssociationType association;
        
        if(this.isAdmin)
            member.addGroupAssociation(group, type);
        else
        {
            for(int i = 0; i < this.groupAssociations.size(); i++)
            {
                if(this.groupAssociations.get(i).getGroup().equals(group))
                    index = i;
            }

            if(index == -1)
                throw new NonMemberExeption("This user is not a member of this group");

            association = this.groupAssociations.get(index).getType();

            if(association.equals(ResearchGroupAssociationType.GROUPLEADER))
                member.addGroupAssociation(group, type);
            else
                throw new UnauthorizedUserExeption("This user is not the group leader of this group");
        }
    }
//Admin/leader adds person to group
    public void addMemberToGroup(Person member, Group group, ResearchGroupAssociationType type, Date startDate)
            throws DateInvalidException, GroupSuspendedException, UnauthorizedUserExeption, NonMemberExeption
    {
        int index = -1;
        ResearchGroupAssociationType association;
        
        if(this.isAdmin)
            member.addGroupAssociation(group, type, startDate);
        else
        {
            for(int i = 0; i < this.groupAssociations.size(); i++)
            {
                if(this.groupAssociations.get(i).getGroup().equals(group))
                    index = i;
            }

            if(index == -1)
                throw new NonMemberExeption("This user is not a member of this group");

            association = this.groupAssociations.get(index).getType();

            if(association.equals(ResearchGroupAssociationType.GROUPLEADER))
                member.addGroupAssociation(group, type, startDate);
            else
                throw new UnauthorizedUserExeption("This user is not the group leader of this group");
        }
    }
//Admin/leader adds person to group
    public void addMemberToGroup(Person member, Group group, ResearchGroupAssociationType type, Date startDate, Date endDate)
            throws DateInvalidException, GroupSuspendedException, UnauthorizedUserExeption, NonMemberExeption
    {
        int index = -1;
        ResearchGroupAssociationType association;
        
        if(this.isAdmin)
            member.addGroupAssociation(group, type, startDate, endDate);
        else
        {
            for(int i = 0; i < this.groupAssociations.size(); i++)
            {
                if(this.groupAssociations.get(i).getGroup().equals(group))
                    index = i;
            }

            if(index == -1)
                throw new NonMemberExeption("This user is not a member of this group");

            association = this.groupAssociations.get(index).getType();

            if(association.equals(ResearchGroupAssociationType.GROUPLEADER))
                member.addGroupAssociation(group, type, startDate);
            else
                throw new UnauthorizedUserExeption("This user is not the group leader of this group");
        }
    }
    //Admin/leader removes person from group
    public void removeMemberFromGroup(Person member, Group group) 
            throws DateInvalidException, GroupSuspendedException, UnauthorizedUserExeption, NonMemberExeption
    {
        int index = -1;
        ResearchGroupAssociationType association;
        
        if(this.isAdmin)
        {
            group.removeMember(member);
            member.endGroupAssociation(group);
        }
        else
        {
            for(int i = 0; i < this.groupAssociations.size(); i++)
            {
                if(this.groupAssociations.get(i).getGroup().equals(group))
                    index = i;
            }

            if(index == -1)
                throw new NonMemberExeption("This user is not a member of this group");

            association = this.groupAssociations.get(index).getType();

            if(association.equals(ResearchGroupAssociationType.GROUPLEADER))
            {
                group.removeMember(member);
                member.endGroupAssociation(group);
            }
            else
                throw new UnauthorizedUserExeption("This user is not the group leader of this group");
        }
    }
    
    //Admin/leader adds group to super group
    public void addGroupToGroup(Group subGroup, Group superGroup, ResearchGroupAssociationType type)
            throws GroupSuspendedException, UnauthorizedUserExeption
    {
        if(this.isAdmin())
            superGroup.addMember(subGroup);
        else
            throw new UnauthorizedUserExeption("This user is not an admin");       
    }
    
    //function we had before
    public void addGroupAssociation(Group group, ResearchGroupAssociationType type)
            throws GroupSuspendedException
    {
        groupAssociations.add(new ResearchGroupAssociationImplementation(group, type));
        group.addMember(this);
    }
//function we had before
    public void addGroupAssociation(Group group, ResearchGroupAssociationType type, Date startDate)
            throws DateInvalidException,GroupSuspendedException
    {
        groupAssociations.add(new ResearchGroupAssociationImplementation(group, type, startDate));
        group.addMember(this);
    }
    //function we had before
    public void addGroupAssociation(Group group, ResearchGroupAssociationType type, Date startDate, Date endDate)
            throws DateInvalidException, GroupSuspendedException
    {
        groupAssociations.add(new ResearchGroupAssociationImplementation(group, type, startDate, endDate));
        group.addMember(this);
    }
    //function we had before
    public void endGroupAssociation(Group group) 
            throws GroupSuspendedException
    {
        groupAssociations.remove(group);
    }
    //------------------------------------------------------------------
    
    public void suspendGroup(Group group) 
            throws UnauthorizedUserExeption
    {
        if(this.isAdmin)
                group.suspendGroup();
            else
                throw new UnauthorizedUserExeption("This user is not an admin");
    }
    
    public void activateGroup(Group group) 
            throws UnauthorizedUserExeption
    {
        if(this.isAdmin)
                group.suspendGroup();
            else
                throw new UnauthorizedUserExeption("This user is not an admin");
    }
    
    //---------------------------GETTERS AND SETTERS---------------------------------------
    /**
    * Defines a function that returns the firstName of the PersonImplementation
    * @return The first name(s) of the PersonImplementation
    */
    public String getFirstName() { return this.firstName; }
    
    /**
    * Defines a function that returns the surname of the PersonImplementation
    * @return The surname of the PersonImplementation
    */
    public String getSurmame() { return this.surname; }
    
    /**
    * Defines a function that returns the primary email address of the PersonImplementation
    * @return The primary email address of the PersonImplementation
    */
    public String getPrimaryEmail() { return this.primaryEmail; }
    
    /**
    * Defines a function that returns the list of auxiliary email addresses of the PersonImplementation
    * @return The primary email address of the PersonImplementation
    */
    public List<String> getAuxiliaryEmails() { return this.auxiliaryEmails; }
    
    /**
    * Defines a function that returns the user object
    * associated with this PersonImplementation
    * @return The user object associated with this person
    */ 
    public BigInteger getUserID() { return this.userID; }

    public void setFirstName(String firstName){ this.firstName = firstName; }
    
    public void setSurname(String surname){ this.surname = surname;}; 
    
    public void setPrimaryEmail(String primaryEmail) throws InvalidEmailException
    {
        String EMAIL_REGEX = "^[-\\w_\\.+]*[-\\w_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(primaryEmail.matches(EMAIL_REGEX))
            this.primaryEmail = primaryEmail;
        else throw new InvalidEmailException(primaryEmail + " is not a valid email address.");

        this.primaryEmail = primaryEmail;
    };
    
    public void setUser(BigInteger userID){ this.userID = userID;};
    
    public void addAuxiliaryEmails(String newEmail){
        this.auxiliaryEmails.add(newEmail);
    };
    
    public void removeAuxiliaryEmails(String email)
    {
        this.auxiliaryEmails.remove(email);
    };
    
    public void clearAuxiliaryEmails()
    {
        this.auxiliaryEmails.clear();
    };

    //------------------------------------------------------------------
    public void setAsAdmin()
    {
        this.isAdmin = true;
    };
    
    public boolean isAdmin()
    {
        return this.isAdmin;
    };

    
    /*
    *   Member variables
    */
    /**
     * Primary key of the person
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private BigInteger id;
    /**
     * The person's first name(s)
     */
    @Basic
    private String firstName;
    /**
     * The person's surname
     */
    @Basic
    private String surname;
    
    /**
     * The person's primary email address 
     */

    @Basic
    private String primaryEmail;
    
    /**
     * A list of secondary email addresses, 
     * to be added only after person's creation
     */
    @ElementCollection
    private List<String> auxiliaryEmails;
    
    /**
     * An object that contains this person's User
     * If it is NULL, this person is just a normal researcher
     */
    @Basic
    private BigInteger userID;
    
    /**
     * A list of this person's research groups (holds ID of the association)
     */
    @OneToMany(targetEntity = ResearchGroupAssociationImplementation.class)
    private List<ResearchGroupAssociation> groupAssociations;
    
    /**
     * A list of this person's research categories
     */
    @OneToMany(targetEntity = ResearcherCategoryAssociationImplementation.class)
    private List<ResearcherCategoryAssociationImplementation> categoryAssociations;
    
    @Basic
    private boolean isAdmin;
}
