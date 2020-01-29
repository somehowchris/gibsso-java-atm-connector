package ch.bbzsogr.bi.interfaces.services;

import ch.bbzsogr.bi.exceptions.*;
import ch.bbzsogr.bi.interfaces.ServiceInterface;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Card;
import ch.bbzsogr.bi.models.Withdraw;
import ch.bbzsogr.bi.models.enums.Currency;

import java.io.IOException;

/**
 * The interface Card service interface.
 */
public interface CardServiceInterface extends ServiceInterface {

  /**
   * Remove card.
   *
   * @param card the card
   * @throws Exception the exception
   */
  void removeCard(Card card) throws Exception;

  /**
   * Gets card.
   *
   * @param cardNr the card nr
   * @return the card
   * @throws Exception the exception
   */
  Card getCard(String cardNr) throws Exception;

  /**
   * Authenticate card.
   *
   * @param cardNr the card nr
   * @param pin    the pin
   * @return the card
   * @throws Exception the exception
   */
  Card authenticate(String cardNr, String pin) throws Exception;

  /**
   * Create card card.
   *
   * @param account the account
   * @return the card
   * @throws CardCreationException the card creation exception
   * @throws IOException           the io exception
   */
  Card createCard(Account account) throws CardCreationException, IOException;

  /**
   * Lock card.
   *
   * @param cardNr the card nr
   * @throws CardLockException     the card lock exception
   * @throws CardNotFoundException the card not found exception
   * @throws IOException           the io exception
   */
  void lockCard(String cardNr) throws CardLockException, CardNotFoundException, IOException;

  /**
   * Change pin.
   *
   * @param cardNr the card nr
   * @param pin    the pin
   * @throws PinChangeException                  the pin change exception
   * @throws CardNotFoundException               the card not found exception
   * @throws IOException                         the io exception
   * @throws PinDoesNotMeetRequirementsException the pin does not meet requirements exception
   */
  void changePin(String cardNr, String pin) throws PinChangeException, CardNotFoundException, IOException, PinDoesNotMeetRequirementsException;

  /**
   * Withdraw withdraw.
   *
   * @param cardNr     the card nr
   * @param amount     the amount
   * @param currency   the currency
   * @param bancomatId the bancomat id
   * @return the withdraw
   * @throws WithdrawException                   the withdraw exception
   * @throws CardNotFoundException               the card not found exception
   * @throws CardLockedException                 the card locked exception
   * @throws BancomatNotFoundException           the bancomat not found exception
   * @throws CouldNotMeetWithdrawAmountException the could not meet withdraw amount exception
   * @throws CreditAmountExceededException       the credit amount exceeded exception
   * @throws IOException                         the io exception
   */
  Withdraw withdraw(String cardNr, double amount, Currency currency, String bancomatId) throws WithdrawException, CardNotFoundException, CardLockedException, BancomatNotFoundException, CouldNotMeetWithdrawAmountException, CreditAmountExceededException, IOException;

}
