package bi.services;

import bi.controllers.DatabaseController;
import bi.interfaces.repositories.CardRepository;
import bi.models.Account;
import bi.models.Card;
import bi.models.Transaction;
import bi.models.Withdraw;
import bi.utils.RepositoryUtil;

public class CardService {

  CardRepository cardRepository = RepositoryUtil.getRepository(CardRepository.class, DatabaseController.type);

  public void lockCard(String cardNumber){
    Card card = this.cardRepository.find(cardNumber);

    if(card == null){
      // TODO card not found exception
    }

    card.setLocked(true);

    this.cardRepository.update(card);
  }

  public void removeCard(Card card){
    this.cardRepository.delete(card);
  }

  public Withdraw withdraw(Card card){
    return null;
  }

  public Transaction transfer(Card card){
    return null;
  }

  public Card getCard(String cardNr){
    return null;
  }

  public Card createCard(Account account){
    return null;
  }

  public Card authenticate(String cardNr, String pin){
    return null;
  }
}
