package cinema.service;

import cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService extends GenericService<MovieSession> {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);
}
