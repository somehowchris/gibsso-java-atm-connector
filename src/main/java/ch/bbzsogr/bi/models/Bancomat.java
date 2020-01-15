package ch.bbzsogr.bi.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bancomat")
public class Bancomat {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id")
  private String id;

  @Column(name = "location", unique = true)
  private String location;

  @OneToMany(mappedBy = "bancomat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<BillCollection> billCollections = new ArrayList<>();

  @OneToMany(mappedBy = "bancomat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Withdraw> withdraws = new ArrayList<>();

  public Bancomat() {
  }

  public Bancomat(String location, List<BillCollection> billCollections, List<Withdraw> withdraws) {
    this.location = location;
    this.billCollections = billCollections;
    this.withdraws = withdraws;
  }

  public List<Withdraw> getWithdraws() {
    return withdraws;
  }

  public void setWithdraws(List<Withdraw> withdraws) {
    this.withdraws = withdraws;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public List<BillCollection> getBillCollections() {
    return billCollections;
  }

  public void setBillCollections(List<BillCollection> billCollections) {
    this.billCollections = billCollections;
  }
}
