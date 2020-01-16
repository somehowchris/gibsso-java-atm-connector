package ch.bbzsogr.rest.services;

import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.exceptions.*;
import ch.bbzsogr.bi.interfaces.services.CardServiceInterface;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Card;
import ch.bbzsogr.bi.models.Withdraw;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.models.enums.Currency;
import ch.bbzsogr.rest.abstracts.RestService;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

@Service(api = ApiType.REST)
public class CardRestService extends RestService implements CardServiceInterface {

  @Override
  public void removeCard(Card card) throws Exception {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("cards")
              .addPathSegment(card.getPin())
              .build()
          )
          .delete()
          .build()
      ).execute();

    if (response.isSuccessful()) return;

    // TODO create exception
    throw new Exception("Nope");
  }

  @Override
  public Card getCard(String cardNr) throws Exception {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("cards")
              .addPathSegment(cardNr)
              .build()
          )
          .get()
          .build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(Card.class)
        .fromJson(response.body().source());

    // TODO create exception
    throw new Exception("Nope");
  }

  @Override
  public Card authenticate(String cardNr, String pin) throws Exception {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("cards")
              .addPathSegment(cardNr)
              .addPathSegment("authenticate")
              .build()
          )
          .post(
            RequestBody.create(
              pin,
              JSON
            )
          )
          .build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(Card.class)
        .fromJson(response.body().source());

    // TODO create exception
    throw new Exception("Nope");
  }

  @Override
  public Card createCard(Account account) throws CardCreationException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("cards")
              .addPathSegment("account")
              .addPathSegment(account.getIban())
              .build()
          )
          .post(null)
          .build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(Card.class)
        .fromJson(response.body().source());

    throw new CardCreationException(account);
  }

  @Override
  public void lockCard(String cardNr) throws CardLockException, CardNotFoundException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("cards")
              .addPathSegment(cardNr)
              .addPathSegment("lock")
              .build()
          )
          .put(null)
          .build()
      ).execute();

    if (response.isSuccessful()) return;

    throw new CardLockException(cardNr);
  }

  @Override
  public void changePin(String cardNr, String pin) throws PinChangeException, CardNotFoundException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("cards")
              .addPathSegment(cardNr)
              .addPathSegment("pin")
              .build()
          )
          .put(
            RequestBody.create(
              pin,
              JSON
            )
          )
          .build()
      ).execute();

    if (response.isSuccessful()) return;

    throw new PinChangeException();
  }

  @Override
  public Withdraw withdraw(String cardNr, double amount, Currency currency, String bancomatId) throws WithdrawException, CardNotFoundException, CardLockedException, BancomatNotFoundException, CouldNotMeetWithdrawAmountException, CreditAmountExceededException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("cards")
              .addPathSegment(cardNr)
              .addPathSegment("withdraw")
              .addPathSegment(String.valueOf(amount))
              .addPathSegment("bancomat")
              .addPathSegment(bancomatId)
              .build()
          )
          .post(
            RequestBody.create(
              currency.name(),
              JSON
            )
          )
          .build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(Withdraw.class)
        .fromJson(response.body().source());

    throw new WithdrawException();
  }

}
