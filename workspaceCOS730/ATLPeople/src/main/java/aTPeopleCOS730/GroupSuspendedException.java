package aTPeopleCOS730;
import javax.persistence.*;

public class GroupSuspendedException extends Exception {
    public GroupSuspendedException() {}
    public GroupSuspendedException(String message){ super(message); }
}
