package ch.bbzsogr.bi.interfaces.services;

import ch.bbzsogr.bi.exceptions.*;
import ch.bbzsogr.bi.interfaces.ServiceInterface;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.Transaction;
import ch.bbzsogr.rest.exceptions.DBTransactionsViaRestNotSupported;

import javax.security.auth.login.AccountLockedException;
import java.io.IOException;

/**
 * The interface Account service interface.
 */
public interface AccountServiceInterface extends ServiceInterface {

  /**
   * Gets account by iban.
   *
   * @param iban the iban
   * @return the account by iban
   * @throws IOException              the io exception
   * @throws AccountNotFoundException the account not found exception
   */
  Account getAccountByIBAN(String iban) throws IOException, AccountNotFoundException;

  /**
   * Lock account.
   *
   * @param iban the iban
   * @throws AccountLockException     the account lock exception
   * @throws IOException              the io exception
   * @throws AccountNotFoundException the account not found exception
   */
  void lockAccount(String iban) throws AccountLockException, IOException, AccountNotFoundException;

  /**
   * Update account.
   *
   * @param account the account
   * @throws EntityUpdateException the entity update exception
   * @throws IOException           the io exception
   */
  void updateAccount(Account account) throws EntityUpdateException, IOException;

  /**
   * Create account account.
   *
   * @param person the person
   * @return the account
   * @throws AccountCreationException the account creation exception
   * @throws AccountCreationException the account creation exception
   * @throws IOException              the io exception
   */
  Account createAccount(Person person) throws AccountCreationException, AccountCreationException, IOException;

  /**
   * Transfer transaction.
   *
   * @param fromIban    the from iban
   * @param transaction the transaction
   * @return the transaction
   * @throws TransferException                 the transfer exception
   * @throws AccountLockedException            the account locked exception
   * @throws AccountNotFoundException          the account not found exception
   * @throws TooLowCreditBalanceException      the too low credit balance exception
   * @throws NoAccountSpecifiedException       the no account specified exception
   * @throws NoCurrencySpecifiedException      the no currency specified exception
   * @throws SubZeroTransactionAmountException the sub zero transaction amount exception
   * @throws IOException                       the io exception
   */
  Transaction transfer(String fromIban, Transaction transaction) throws TransferException, AccountLockedException, AccountNotFoundException, TooLowCreditBalanceException, NoAccountSpecifiedException, NoCurrencySpecifiedException, SubZeroTransactionAmountException, IOException;

  /**
   * Transfer transaction.
   *
   * @param fromIban      the from iban
   * @param transaction   the transaction
   * @param dbTransaction the db transaction
   * @return the transaction
   * @throws TransferException                 the transfer exception
   * @throws AccountLockedException            the account locked exception
   * @throws AccountNotFoundException          the account not found exception
   * @throws TooLowCreditBalanceException      the too low credit balance exception
   * @throws NoAccountSpecifiedException       the no account specified exception
   * @throws NoCurrencySpecifiedException      the no currency specified exception
   * @throws SubZeroTransactionAmountException the sub zero transaction amount exception
   * @throws DBTransactionsViaRestNotSupported the db transactions via rest not supported
   */
  Transaction transfer(String fromIban, Transaction transaction, org.hibernate.Transaction dbTransaction) throws TransferException, AccountLockedException, AccountNotFoundException, TooLowCreditBalanceException, NoAccountSpecifiedException, NoCurrencySpecifiedException, SubZeroTransactionAmountException, DBTransactionsViaRestNotSupported;

}
