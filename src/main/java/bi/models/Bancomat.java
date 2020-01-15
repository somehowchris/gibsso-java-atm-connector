package bi.models;

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

  public Bancomat() {
  }

  public Bancomat(String location, List<BillCollection> billCollections) {
    this.location = location;
    this.billCollections = billCollections;
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
