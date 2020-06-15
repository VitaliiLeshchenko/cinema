package cinema.dao;

public interface GenericDao<T> {
    T getById(Long id);
}
