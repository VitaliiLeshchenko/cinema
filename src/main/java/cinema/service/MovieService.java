package cinema.service;

import cinema.model.Movie;
import java.util.List;

public interface MovieService extends GenericService<Movie> {
    Movie add(Movie movie);

    List<Movie> getAll();
}
