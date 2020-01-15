package bi.models.enums;

public enum DatabaseInterpreters {
  OGM("OGM"), ORM("ORM"), FS("FS");

  String prefix;

  DatabaseInterpreters(String prefix){
    this.prefix = prefix;
  }

  public String getPrefix(){
    return this.prefix;
  }
}
