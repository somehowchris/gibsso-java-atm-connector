package ch.bbzsogr.bi.models.enums;

public enum EnvKeys {
  BANK_MAIL("BANK_MAIL");

  private String key;

  EnvKeys(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
