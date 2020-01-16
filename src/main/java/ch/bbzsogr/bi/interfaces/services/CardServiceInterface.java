package ch.bbzsogr.bi.interfaces.services;

import ch.bbzsogr.bi.exceptions.*;
import ch.bbzsogr.bi.interfaces.ServiceInterface;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Card;
import ch.bbzsogr.bi.models.Withdraw;
import ch.bbzsogr.bi.models.enums.Currency;

import java.io.IOException;

public interface CardServiceInterface extends ServiceInterface {

  void removeCard(Card card) throws Exception;

  Card getCard(String cardNr) throws Exception;

  Card authenticate(String cardNr, String pin) throws Exception;

  Card createCard(Account account) throws CardCreationException, IOException;

  void lockCard(String cardNr) throws CardLockException, CardNotFoundException, IOException;

  void changePin(String cardNr, String pin) throws PinChangeException, CardNotFoundException, IOException, PinDoesNotMeetRequirementsException;

  Withdraw withdraw(String cardNr, double amount, Currency currency, String bancomatId) throws WithdrawException, CardNotFoundException, CardLockedException, BancomatNotFoundException, CouldNotMeetWithdrawAmountException, CreditAmountExceededException, IOException;

}
