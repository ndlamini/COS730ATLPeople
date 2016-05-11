package defaultImpl;

import java.math.BigInteger;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import aTPeopleCOS730.Entity;

@javax.persistence.Entity
public class EntityImplementation implements Entity
{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private BigInteger id;
}
