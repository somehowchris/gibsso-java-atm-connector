package services;

import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Card;
import ch.bbzsogr.bi.utils.HashUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CardServiceTest {

    @Test
    public void lockCard_CardIsLocked(){
        Card card = new Card();
        card.setLocked(true);

        Assert.assertTrue(card.isLocked());
    }

    @Test
    public void lockCard_CardIsNotLocked(){
        Card card = new Card();
        card.setLocked(false);

        Assert.assertFalse(card.isLocked());
    }

    @Test
    public void createCard_CardIsNotNull(){
        Card card = new Card();
        Account account = new Account();

        String pin = card.getPin();
        card.setPin(HashUtil.hash(pin));

        account.getCards().add(card);
        card.setAccount(account);
        card.setPin(pin);

        Assert.assertNotNull(card);
        Assert.assertNotNull(card.getPin());
        Assert.assertNotNull(card.getAccount());
    }

    @Test
    public void createCard_CardIsNull(){
        Card card = new Card();
        card.setPin(null);

        Assert.assertNull(card.getPin());
        Assert.assertNull(card.getAccount());
    }

    @Test
    public void changePin_PinEqualsSetPin(){
        Card card = new Card();
        String pin = "123456";
        card.setPin(pin);
        Assert.assertEquals(card.getPin(), "123456");

        String newPin = "654321";
        card.setPin(newPin);
        Assert.assertNotEquals(card.getPin(), "123456");
        Assert.assertEquals(card.getPin(), "654321");
    }

    @Test
    public void changePin_PinDoesNotEqualSetPin(){
        Card card = new Card();
        String pin = "123456";
        card.setPin("123456");
        Assert.assertEquals(card.getPin(), "123456");
        card.setPin(HashUtil.hash(pin));
        Assert.assertNotEquals(card.getPin(), "123456");
    }
}
