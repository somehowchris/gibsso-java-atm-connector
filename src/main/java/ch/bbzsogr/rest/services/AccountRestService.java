package ch.bbzsogr.rest.services;

import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.exceptions.*;
import ch.bbzsogr.bi.interfaces.services.AccountServiceInterface;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.Transaction;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.rest.abstracts.RestService;
import ch.bbzsogr.rest.exceptions.DBTransactionsViaRestNotSupported;
import okhttp3.RequestBody;
import okhttp3.Response;

import javax.security.auth.login.AccountLockedException;
import java.io.IOException;

@Service(api = ApiType.REST)
public class AccountRestService extends RestService implements AccountServiceInterface {

  @Override
  public Account getAccountByIBAN(String iban) throws IOException, AccountNotFoundException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("accounts")
              .addPathSegment(iban)
              .build()
          ).build()
      ).execute();

    if (response.isSuccessful()) return getMoshiAdapter().adapter(Account.class).fromJson(response.body().source());

    throw new AccountNotFoundException(iban);
  }

  @Override
  public void lockAccount(String iban) throws AccountLockException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("accounts")
              .addPathSegment("lock")
              .addPathSegment(iban)
              .build()
          ).build()
      ).execute();

    if (response.isSuccessful()) return;

    throw new AccountLockException(iban);
  }

  @Override
  public void updateAccount(Account account) throws EntityUpdateException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("accounts")
              .addPathSegment(account.getIban())
              .build()
          )
          .put(
            RequestBody.create(
              getMoshiAdapter()
                .adapter(Account.class)
                .toJson(account),
              JSON
            )
          ).build()
      ).execute();

    if (response.isSuccessful()) return;

    throw new EntityUpdateException(Account.class);
  }

  @Override
  public Account createAccount(Person person) throws AccountCreationException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("accounts")
              .addPathSegment("person")
              .addPathSegment(person.getId())
              .build()
          )
          .post(null)
          .build()
      ).execute();

    if (response.isSuccessful()) return getMoshiAdapter().adapter(Account.class).fromJson(response.body().source());

    throw new AccountCreationException(person);
  }

  @Override
  public Transaction transfer(String fromIban, Transaction transaction) throws TransferException, AccountLockedException, AccountNotFoundException, TooLowCreditBalanceException, NoAccountSpecifiedException, NoCurrencySpecifiedException, SubZeroTransactionAmountException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("accounts")
              .addPathSegment(fromIban)
              .addPathSegment("transfer")
              .build()
          )
          .post(
            RequestBody.create(
              getMoshiAdapter()
                .adapter(Transaction.class)
                .toJson(transaction),
              JSON
            )
          )
          .build()
      ).execute();

    if (response.isSuccessful()) return getMoshiAdapter().adapter(Transaction.class).fromJson(response.body().source());

    throw new TransferException();
  }

  @Override
  public Transaction transfer(String fromIban, Transaction transaction, org.hibernate.Transaction dbTransaction) throws TransferException, AccountLockedException, AccountNotFoundException, TooLowCreditBalanceException, NoAccountSpecifiedException, NoCurrencySpecifiedException, SubZeroTransactionAmountException, DBTransactionsViaRestNotSupported {
    throw new DBTransactionsViaRestNotSupported();
  }

}
