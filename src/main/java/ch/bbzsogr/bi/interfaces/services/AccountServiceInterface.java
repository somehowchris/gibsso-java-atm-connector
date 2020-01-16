package ch.bbzsogr.bi.interfaces.services;

import ch.bbzsogr.bi.exceptions.*;
import ch.bbzsogr.bi.interfaces.ServiceInterface;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.Transaction;
import ch.bbzsogr.rest.exceptions.DBTransactionsViaRestNotSupported;

import javax.security.auth.login.AccountLockedException;
import java.io.IOException;

public interface AccountServiceInterface extends ServiceInterface {

  Account getAccountByIBAN(String iban) throws IOException, AccountNotFoundException;

  void lockAccount(String iban) throws AccountLockException, IOException, AccountNotFoundException;

  void updateAccount(Account account) throws EntityUpdateException, IOException;

  Account createAccount(Person person) throws AccountCreationException, AccountCreationException, IOException;

  Transaction transfer(String fromIban, Transaction transaction) throws TransferException, AccountLockedException, AccountNotFoundException, TooLowCreditBalanceException, NoAccountSpecifiedException, NoCurrencySpecifiedException, SubZeroTransactionAmountException, IOException;

  Transaction transfer(String fromIban, Transaction transaction, org.hibernate.Transaction dbTransaction) throws TransferException, AccountLockedException, AccountNotFoundException, TooLowCreditBalanceException, NoAccountSpecifiedException, NoCurrencySpecifiedException, SubZeroTransactionAmountException, DBTransactionsViaRestNotSupported;

}
