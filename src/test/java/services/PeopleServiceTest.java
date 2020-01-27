package services;

import ch.bbzsogr.bi.models.Person;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class PeopleServiceTest {

    @Test
    public void createPerson_PersonNotNull(){
        Person person = new Person();
        person.setFirstName("Hans");
        person.setLastName("Müller");
        person.setEmail("Hans@Müller.com");

        Assert.assertNotNull(person);
        Assert.assertNotNull(person.getFirstName());
        Assert.assertNotNull(person.getLastName());
        Assert.assertNotNull(person.getEmail());
    }

    @Test
    public void createPerson_PersonIsNull(){
        Person person = new Person();

        Assert.assertNull(person.getFirstName());
        Assert.assertNull(person.getLastName());
        Assert.assertNull(person.getEmail());
    }

}
