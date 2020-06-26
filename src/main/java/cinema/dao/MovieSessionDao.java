package cinema.dao;

import cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao extends GenericDao<MovieSession> {
    List<MovieSession> findAvailableSessions(Long movieID, LocalDate date);
}
