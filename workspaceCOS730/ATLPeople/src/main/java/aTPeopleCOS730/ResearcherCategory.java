package aTPeopleCOS730;
import java.util.List;

public interface ResearcherCategory
{
    void addState(ResearchCategoryState stateID);
    
    void removeState(ResearchCategoryState stateID);
    
    List<ResearchCategoryState> getStateIDs();  
}
