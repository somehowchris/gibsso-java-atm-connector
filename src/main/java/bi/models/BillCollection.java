package bi.models;

import bi.models.enums.Currency;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="bill_collection")
public class BillCollection {

  @Id @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  private String id;

  @Column(name="worth")
  private double worth = 0.0;

  @Column(name="amount")
  private int amount = 0;

  @Enumerated(EnumType.STRING)
  private Currency currency = Currency.CHF;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bancomat_id", referencedColumnName = "id")
  private Bancomat bancomat;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "withdraw_id", referencedColumnName = "id")
  private Withdraw withdraw;

  public BillCollection() {
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

  public Bancomat getBancomat() {
    return bancomat;
  }

  public void setBancomat(Bancomat bancomat) {
    this.bancomat = bancomat;
  }

  public Withdraw getWithdraw() {
    return withdraw;
  }

  public void setWithdraw(Withdraw withdraw) {
    this.withdraw = withdraw;
  }
}
