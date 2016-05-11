package aTPeopleCOS730;
import javax.persistence.*;

public class NonMemberExeption extends Exception {
    public NonMemberExeption() {}
    public NonMemberExeption(String message){ super(message); }
}