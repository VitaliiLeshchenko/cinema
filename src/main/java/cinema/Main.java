package cinema;

import cinema.model.Movie;
import cinema.service.MovieService;
import cinema.util.Injector;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Another title");
        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
