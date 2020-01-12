package bi.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class Bancomat {

  @Id
  @GeneratedValue(generator = "uuid")
  private String id;

  private String location;

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
