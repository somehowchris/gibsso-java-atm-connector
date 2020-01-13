package bi.interfaces.repositories;

public interface Repository<T> {
  T find(T obj);
  T save(T obj);
  void update(T obj);
  void delete(T obj);
}
