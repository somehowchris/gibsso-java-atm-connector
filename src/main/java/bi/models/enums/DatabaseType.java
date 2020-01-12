package bi.models.enums;

public enum DatabaseType {
  OGM("OGM"), ORM("ORM"), FS("FS");

  String prefix;

  DatabaseType(String prefix){
    this.prefix = prefix;
  }

  public String getPrefix(){
    return this.prefix;
  }
}
