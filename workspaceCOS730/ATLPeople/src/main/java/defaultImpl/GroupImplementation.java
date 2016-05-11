/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultImpl;

import aTPeopleCOS730.GroupSuspendedException;
import aTPeopleCOS730.Person;
import aTPeopleCOS730.ResearchGroupAssociationType;

import aTPeopleCOS730.Entity;
import aTPeopleCOS730.Group;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Research GroupImplementation type, defined as an Entity
 */
@javax.persistence.Entity

public class GroupImplementation implements Group {
    /**
    *  Defines the no-args constructor for a Research GroupImplementation
    *  Protected, as it should not be used
    *  (especially not outside of the people package)
    */
    protected GroupImplementation() {}
    
    /**
    * Defines the interface of the standard constructor for
    * an empty Research GroupImplementation
    * @param    name    The name of the research group
    */
    public GroupImplementation(String name)
    {
        this.members = new ArrayList<Entity>();
        this.name = name;
        isActive = true;
    }
    
    /**
    * Defines the interface for a constructor to make a group already 
    * containing a list of members
    * @param    name    The name of the research group
    * @param    members A list of the members already belonging to this group
    */
    public GroupImplementation(String name, List<Entity> members)
    {
        this.name = name;
        this.members = members;
        isActive = true;
    }


    public BigInteger getId() {
        return this.id;
    }

    /**
    * Defines a function that returns the name of this Research GroupImplementation
    * @return The name of the Research GroupImplementation
    */
    public String getName() { return this.name; }
    
    /**
    * Defines a function that returns the list of members 
    * associated with this group
    * @return The list of members in the Research GroupImplementation
    */
    public List<Entity> getMembers()
    { 
        return this.members;
    }
    
    /**
     * Adds an Entity to the group's members list. This can be used for Group entities
     * 
     * @param newMember
     * @throws GroupSuspendedException
     */
    public void addMember(Entity newMember) throws GroupSuspendedException
    {
        if(!isActive) 
            throw new GroupSuspendedException("The group is suspended and cannot be edited currently.");
        
        this.members.add((Person) newMember);
    }
    
    public void removeMember(Entity member) throws GroupSuspendedException
    {
        if(!isActive) throw new GroupSuspendedException("The group is suspended and cannot be edited currently.");
        this.members.remove(member);
    }
    
    public void memberQuits(Entity member) throws GroupSuspendedException
    {
        if(!isActive) throw new GroupSuspendedException("The group is suspended and cannot be edited currently.");
        this.members.remove(member);
    }
    
    public void activateGroup()
    {
        isActive = true;
    }
    
    public void suspendGroup()
    {
        isActive = false;
    }
    
    public boolean getGroupState()
    {
        return isActive;
    }
    
    public boolean isMember(Entity member)
    {
        if(this.members.contains(member))
            return true;
        else
            return false;
    }
    
    public boolean containsDuplicate(Entity entity)
    {
        if(members.contains(entity))
            return true;
        else        
            return false;
    }

    /*
     * Member variables:
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private BigInteger id;
    /**
     * The name of the Research Group
    */
    @Basic
    private String name;
    
    /**
    * The current status of the Research Group (active/inactive)
    */
    @Basic
    private Boolean isActive;
  
    /**
    * The list of members that belong to this group, of type Entity
    */
    @OneToMany(targetEntity = EntityImplementation.class)
    private List<Entity> members;
   
    
}
