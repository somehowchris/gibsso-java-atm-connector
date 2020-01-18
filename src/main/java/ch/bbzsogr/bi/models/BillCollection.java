package ch.bbzsogr.bi.models;

import ch.bbzsogr.bi.models.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bancomat_id", referencedColumnName = "id")
  private Bancomat bancomat;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "withdraw_id", referencedColumnName = "id")
  private Withdraw withdraw;

  public BillCollection() {
  }

  public BillCollection(BillCollection bill, Withdraw withdraw) {
    this.worth = bill.getWorth();
    this.amount = bill.getAmount();
    this.currency = bill.getCurrency();
    this.withdraw = withdraw;
  }

  public BillCollection(double worth, int amount, Currency currency, Bancomat bancomat, Withdraw withdraw) {
    this.worth = worth;
    this.amount = amount;
    this.currency = currency;
    this.bancomat = bancomat;
    this.withdraw = withdraw;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getWorth() {
    return worth;
  }

  public void setWorth(double worth) {
    this.worth = worth;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  @JsonIgnore
  public Bancomat getBancomat() {
    return bancomat;
  }

  public void setBancomat(Bancomat bancomat) {
    this.bancomat = bancomat;
  }

  @JsonIgnore
  public Withdraw getWithdraw() {
    return withdraw;
  }

  public void setWithdraw(Withdraw withdraw) {
    this.withdraw = withdraw;
  }
}
