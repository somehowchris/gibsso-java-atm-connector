package bi.services;

import bi.controllers.DatabaseController;
import bi.decorators.Service;
import bi.interfaces.repositories.CardRepository;
import bi.models.Account;
import bi.models.Card;
import bi.models.Transaction;
import bi.models.Withdraw;
import bi.utils.Container;
import bi.utils.DotEnvUtil;

@Service()
public class CardService {

  CardRepository cardRepository = Container.getRepository(CardRepository.class, DatabaseController.type);

  DotEnvUtil envUtil = new DotEnvUtil();

  public void lockCard(String cardNumber) {
    Card card = this.cardRepository.find(cardNumber);

    if (card == null) {
      // TODO card not found exception
      return;
    }

    card.setLocked(true);

    this.cardRepository.update(card);
  }

  public void removeCard(Card card) {
    this.cardRepository.delete(card);
  }

  public Withdraw withdraw(Card card) {
    String email = envUtil.get("BANK_EMAIL");
    return null;
  }

  public Transaction transfer(Card card) {
    return null;
  }

  public Card getCard(String cardNr) {
    return null;
  }

  public Card createCard(Account account) {
    return null;
  }

  public Card authenticate(String cardNr, String pin) {
    return null;
  }
}
