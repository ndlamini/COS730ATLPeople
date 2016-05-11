package aTPeopleCOS730;
import javax.persistence.*;

public class UnauthorizedUserExeption extends Exception {
    public UnauthorizedUserExeption() {}
    public UnauthorizedUserExeption(String message){ super(message); }
}
