package ch.bbzsogr.bi.models;

import ch.bbzsogr.bi.utils.CreditCardUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Card.
 */
@Entity
@Table(name = "card")
public class Card {

  @Id
  @Column(name = "card_number")
  private String cardNumber = CreditCardUtil.generateCardNumber();

  @Column(name = "pin")
  private String pin = CreditCardUtil.generatePin();

  @Column(name = "expires_at")
  private Date expiresAt = Date.from(new Date().toInstant().plus(Duration.ofDays(365 * 4)));

  @Column(name = "locked", columnDefinition = "tinyint default false")
  private boolean locked = false;

  @Column(name = "credit")
  private double credit = 2000.0;

  @Column(name = "max_withdraw")
  private double maxWithdraw = 2000.0;

  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Withdraw> withdraws = new ArrayList<>();

  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Transaction> transactions = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_iban", referencedColumnName = "iban", nullable = false)
  private Account account;

    /**
     * Instantiates a new Card.
     */
    public Card() {
  }

    /**
     * Instantiates a new Card.
     *
     * @param cardNumber   the card number
     * @param pin          the pin
     * @param expiresAt    the expires at
     * @param locked       the locked
     * @param credit       the credit
     * @param maxWithdraw  the max withdraw
     * @param withdraws    the withdraws
     * @param transactions the transactions
     * @param account      the account
     */
    public Card(String cardNumber, String pin, Date expiresAt, boolean locked, double credit, double maxWithdraw, List<Withdraw> withdraws, List<Transaction> transactions, Account account) {
    this.cardNumber = cardNumber;
    this.pin = pin;
    this.expiresAt = expiresAt;
    this.locked = locked;
    this.credit = credit;
    this.maxWithdraw = maxWithdraw;
    this.withdraws = withdraws;
    this.transactions = transactions;
    this.account = account;
  }

    /**
     * Gets card number.
     *
     * @return the card number
     */
    public String getCardNumber() {
    return cardNumber;
  }

    /**
     * Sets card number.
     *
     * @param cardNumber the card number
     */
    public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

    /**
     * Gets pin.
     *
     * @return the pin
     */
    public String getPin() {
    return pin;
  }

    /**
     * Sets pin.
     *
     * @param pin the pin
     */
    public void setPin(String pin) {
    this.pin = pin;
  }

    /**
     * Gets expires at.
     *
     * @return the expires at
     */
    public Date getExpiresAt() {
    return expiresAt;
  }

    /**
     * Sets expires at.
     *
     * @param expiresAt the expires at
     */
    public void setExpiresAt(Date expiresAt) {
    this.expiresAt = expiresAt;
  }

    /**
     * Is locked boolean.
     *
     * @return the boolean
     */
    public boolean isLocked() {
    return locked;
  }

    /**
     * Sets locked.
     *
     * @param locked the locked
     */
    public void setLocked(boolean locked) {
    this.locked = locked;
  }

    /**
     * Gets credit.
     *
     * @return the credit
     */
    public double getCredit() {
    return credit;
  }

    /**
     * Sets credit.
     *
     * @param credit the credit
     */
    public void setCredit(double credit) {
    this.credit = credit;
  }

    /**
     * Gets max withdraw.
     *
     * @return the max withdraw
     */
    public double getMaxWithdraw() {
    return maxWithdraw;
  }

    /**
     * Sets max withdraw.
     *
     * @param maxWithdraw the max withdraw
     */
    public void setMaxWithdraw(double maxWithdraw) {
    this.maxWithdraw = maxWithdraw;
  }

    /**
     * Gets withdraws.
     *
     * @return the withdraws
     */
    public List<Withdraw> getWithdraws() {
    return withdraws;
  }

    /**
     * Sets withdraws.
     *
     * @param withdraws the withdraws
     */
    public void setWithdraws(List<Withdraw> withdraws) {
    this.withdraws = withdraws;
  }

    /**
     * Gets transactions.
     *
     * @return the transactions
     */
    public List<Transaction> getTransactions() {
    return transactions;
  }

    /**
     * Sets transactions.
     *
     * @param transactions the transactions
     */
    public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

    /**
     * Gets account.
     *
     * @return the account
     */
    @JsonIgnore
  public Account getAccount() {
    return account;
  }

    /**
     * Sets account.
     *
     * @param account the account
     */
    public void setAccount(Account account) {
    this.account = account;
  }

    /**
     * After load.
     */
    @PostLoad()
  public void afterLoad() {
    this.setPin(null);
  }
}
