package ch.bbzsogr.bi.models;

import ch.bbzsogr.bi.models.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The type Bill collection.
 */
@Entity
@Table(name = "bill_collection")
public class BillCollection {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id")
  private String id;

  @Column(name = "worth")
  private double worth = 0.0;

  @Column(name = "amount")
  private int amount = 0;

  @Enumerated(EnumType.STRING)
  private Currency currency = Currency.CHF;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bancomat_id", referencedColumnName = "id")
  private Bancomat bancomat;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "withdraw_id", referencedColumnName = "id")
  private Withdraw withdraw;

  /**
   * Instantiates a new Bill collection.
   */
  public BillCollection() {
  }

  /**
   * Instantiates a new Bill collection.
   *
   * @param bill     the bill
   * @param withdraw the withdraw
   */
  public BillCollection(BillCollection bill, Withdraw withdraw) {
    this.worth = bill.getWorth();
    this.amount = bill.getAmount();
    this.currency = bill.getCurrency();
    this.withdraw = withdraw;
  }

  /**
   * Instantiates a new Bill collection.
   *
   * @param worth    the worth
   * @param amount   the amount
   * @param currency the currency
   * @param bancomat the bancomat
   * @param withdraw the withdraw
   */
  public BillCollection(double worth, int amount, Currency currency, Bancomat bancomat, Withdraw withdraw) {
    this.worth = worth;
    this.amount = amount;
    this.currency = currency;
    this.bancomat = bancomat;
    this.withdraw = withdraw;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets worth.
   *
   * @return the worth
   */
  public double getWorth() {
    return worth;
  }

  /**
   * Sets worth.
   *
   * @param worth the worth
   */
  public void setWorth(double worth) {
    this.worth = worth;
  }

  /**
   * Gets amount.
   *
   * @return the amount
   */
  public int getAmount() {
    return amount;
  }

  /**
   * Sets amount.
   *
   * @param amount the amount
   */
  public void setAmount(int amount) {
    this.amount = amount;
  }

  /**
   * Gets currency.
   *
   * @return the currency
   */
  public Currency getCurrency() {
    return currency;
  }

  /**
   * Sets currency.
   *
   * @param currency the currency
   */
  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  /**
   * Gets bancomat.
   *
   * @return the bancomat
   */
  @JsonIgnore
  public Bancomat getBancomat() {
    return bancomat;
  }

  /**
   * Sets bancomat.
   *
   * @param bancomat the bancomat
   */
  public void setBancomat(Bancomat bancomat) {
    this.bancomat = bancomat;
  }

  /**
   * Gets withdraw.
   *
   * @return the withdraw
   */
  @JsonIgnore
  public Withdraw getWithdraw() {
    return withdraw;
  }

  /**
   * Sets withdraw.
   *
   * @param withdraw the withdraw
   */
  public void setWithdraw(Withdraw withdraw) {
    this.withdraw = withdraw;
  }
}
