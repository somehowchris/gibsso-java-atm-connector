package ch.bbzsogr.bi.models.enums;

/**
 * The enum Database interpreters.
 */
public enum DatabaseInterpreters {
  /**
   * Ogm database interpreters.
   */
  OGM("OGM"),
  /**
   * Orm database interpreters.
   */
  ORM("ORM"),
  /**
   * Fs database interpreters.
   */
  FS("FS");

  /**
   * The Prefix.
   */
  String prefix;

  DatabaseInterpreters(String prefix) {
    this.prefix = prefix;
  }

  /**
   * Gets prefix.
   *
   * @return the prefix
   */
  public String getPrefix() {
    return this.prefix;
  }
}
