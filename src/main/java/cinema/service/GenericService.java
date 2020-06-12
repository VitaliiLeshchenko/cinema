package cinema.service;

import cinema.model.CinemaHall;

public interface GenericService<T> {
    T getById(Long id);
}
