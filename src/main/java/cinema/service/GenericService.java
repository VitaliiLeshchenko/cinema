package cinema.service;

public interface GenericService<T> {
    T getById(Long id);
}
