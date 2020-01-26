package services;

import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

public class AccountServiceTest {

    @Test
    public void createAccount_AccountIsNotNull(){
        Account account = new Account();
        Person person = new Person();
        person.setFirstName("Jonathan");
        person.setLastName("Joestar");
        account.setPerson(person);
        account.setBalance(321564564);

        Assert.assertNotNull(account);
        Assert.assertNotNull(account.getPerson());
        Assert.assertEquals(person.getFirstName(), "Jonathan");
        Assert.assertEquals(person.getLastName(), "Joestar");
        Assert.assertNotNull(account.getBalance());
        Assert.assertEquals(account.getBalance(), 321564564, 321564564);
    }

    @Test
    public void createAccount_AccountIsNull(){
        Account account = new Account();
        Assert.assertNull(account.getPerson());
        Assert.assertEquals(account.getBalance(), 100, 100);
    }

    @Test
    public void lockAccount_AccountIsLocked(){
        Account account = new Account();

        account.setCards(null);
        account.setLocked(true);

        Assert.assertTrue(account.isLocked());
    }

    @Test
    public void lockAccount_AccountIsNotLocked(){
        Account account = new Account();

        account.setCards(null);
        account.setLocked(false);

        Assert.assertFalse(account.isLocked());
    }
}
