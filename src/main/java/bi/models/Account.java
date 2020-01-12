package bi.models;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

public class Account {

  @Id
  private String iban = Iban.random(CountryCode.AT).toFormattedString();

  private double balance = 0.0;
  private boolean locked = false;

  private List<Card> cards = new ArrayList<>();
  private List<Transaction> transactions = new ArrayList<>();
  private List<Withdraw> withdraws = new ArrayList<>();

  public Account() {
  }

  public Account(String iban, double balance, boolean locked, List<Card> cards, List<Transaction> transactions, List<Withdraw> withdraws) {
    this.iban = iban;
    this.balance = balance;
    this.locked = locked;
    this.cards = cards;
    this.transactions = transactions;
    this.withdraws = withdraws;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public List<Card> getCards() {
    return cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public List<Withdraw> getWithdraws() {
    return withdraws;
  }

  public void setWithdraws(List<Withdraw> withdraws) {
    this.withdraws = withdraws;
  }
}
