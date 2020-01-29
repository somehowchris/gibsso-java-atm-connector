package ch.bbzsogr.bi.models.enums;

/**
 * The enum Env keys.
 */
public enum EnvKeys {
  /**
   * Bank mail env keys.
   */
  BANK_MAIL("BANK_MAIL");

  private String key;

  EnvKeys(String key) {
    this.key = key;
  }

  /**
   * Gets key.
   *
   * @return the key
   */
  public String getKey() {
    return key;
  }
}
