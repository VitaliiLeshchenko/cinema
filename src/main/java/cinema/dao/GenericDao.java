package cinema.dao;

import java.util.List;

public interface GenericDao<T> {
    T add(T item);

    T getById(Long id);

    List<T> getAll();
}
