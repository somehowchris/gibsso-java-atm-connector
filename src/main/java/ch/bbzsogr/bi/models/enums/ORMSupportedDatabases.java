package ch.bbzsogr.bi.models.enums;

import ch.bbzsogr.bi.exceptions.UrlDialectNotSupportedException;

/**
 * The enum Orm supported databases.
 */
public enum ORMSupportedDatabases {
  /**
   * H 2 orm supported databases.
   */
  H2("org.h2.Driver", "org.hibernate.dialect.H2Dialect"),
  /**
   * Maria orm supported databases.
   */
  Maria("org.mariadb.jdbc.Driver", "org.hibernate.dialect.MariaDB53Dialect"),
  /**
   * My sql orm supported databases.
   */
  MySQL("com.mysql.cj.jdbc.Driver", "org.hibernate.dialect.MySQL8Dialect"),
  /**
   * Mssql orm supported databases.
   */
  MSSQL("com.microsoft.sqlserver.jdbc.SQLServerDriver", "org.hibernate.dialect.SQLServer2012Dialect"),
  /**
   * Postgre sql orm supported databases.
   */
  PostgreSQL("org.postgresql.Driver", "org.hibernate.dialect.PostgreSQL95Dialect"),
  /**
   * Sq lite orm supported databases.
   */
  SQLite("org.sqlite.JDBC", "org.hibernate.dialect.SQLiteDialect");

  private String dialect;
  private String hibernateDialect;

  ORMSupportedDatabases(String dialect, String hibernateDialect) {
    this.dialect = dialect;
    this.hibernateDialect = hibernateDialect;
  }

  /**
   * Get orm supported databases.
   *
   * @param url the url
   * @return the orm supported databases
   * @throws UrlDialectNotSupportedException the url dialect not supported
   */
  public static ORMSupportedDatabases get(String url) throws UrlDialectNotSupportedException {
    if (url.startsWith("jdbc:h2:")) {
      return H2;
    } else if (url.startsWith("jdbc:maria:")) {
      return Maria;
    } else if (url.startsWith("jdbc:mysql:")) {
      return MySQL;
    } else if (url.startsWith("jdbc:mssql:")) {
      return MSSQL;
    } else if (url.startsWith("jdbc:postgresql:")) {
      return PostgreSQL;
    } else if (url.startsWith("jdbc:sqlite:")) {
      return SQLite;
    }
    throw new UrlDialectNotSupportedException("null", "null");
  }

  /**
   * Get dialect string.
   *
   * @return the string
   */
  public String getDialect() {
    return this.dialect;
  }

  public String getHibernateDialect() {
    return this.hibernateDialect;
  }
}
