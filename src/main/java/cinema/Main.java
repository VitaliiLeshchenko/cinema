package cinema;

import cinema.dao.UserDao;
import cinema.exception.AuthenticationException;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import cinema.util.Injector;
import java.time.LocalDateTime;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("cinema");

    public static void main(String[] args) throws AuthenticationException {
        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        Movie movie1 = new Movie();
        movie1.setTitle("Terminator");
        movie1.setDescription("Film about machineLearning");
        movie1 = movieService.add(movie1);
        Movie movie2 = new Movie();
        movie2.setTitle("Terminator2");
        movie2.setDescription("Film not about machineLearning");
        movie2 = movieService.add(movie2);
        System.out.println("---------******************---------");
        movieService.getAll().forEach(System.out::println);
        System.out.println("---------******************---------");

        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(20);
        cinemaHall1.setDescription("Small hall");
        CinemaHallService cinemaHallService
                = (CinemaHallService) INJECTOR.getInstance(CinemaHallService.class);
        cinemaHall1 = cinemaHallService.add(cinemaHall1);
        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(200);
        cinemaHall2.setDescription("Big hall");
        cinemaHall2 = cinemaHallService.add(cinemaHall2);
        System.out.println("---------******************---------");
        cinemaHallService.getAll().forEach(System.out::println);
        System.out.println("---------******************---------");

        LocalDateTime time1 = LocalDateTime.now();
        LocalDateTime time2 = LocalDateTime.of(2020, 5,21,15,30);
        LocalDateTime time3 = LocalDateTime.of(2020, 5,21,16,30);
        LocalDateTime time4 = LocalDateTime.of(9999, 5,21,17,30);

        MovieSessionService movieSessionService
                = (MovieSessionService) INJECTOR.getInstance(MovieSessionService.class);
        MovieSession movieSession1 = movieSessionService.add(
                new MovieSession(movie1, cinemaHall1, time1));
        MovieSession movieSession2 = movieSessionService.add(
                new MovieSession(movie1, cinemaHall2, time1));
        MovieSession movieSession3 = movieSessionService.add(
                new MovieSession(movie2, cinemaHall2, time2));
        MovieSession movieSession4 = movieSessionService.add(
                new MovieSession(movie2, cinemaHall1, time2));
        MovieSession movieSession5 = movieSessionService.add(
                new MovieSession(movie2, cinemaHall2, time3));
        MovieSession movieSession6 = movieSessionService.add(
                new MovieSession(movie1, cinemaHall2, time4));
        System.out.println("---------******************---------");
        movieSessionService.findAvailableSessions(movie1.getId(),
                time1.toLocalDate()).forEach(System.out::println);
        System.out.println("---------******************---------");

        UserDao userDao = (UserDao) INJECTOR.getInstance(UserDao.class);
        User user = new User("123@gmail.com", "password");
        user = userDao.add(user);
        System.out.println(user);
        System.out.println(userDao.findByEmail("123@gmail.com"));

        AuthenticationService authenticationService
                = (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);
        User user1 = authenticationService.register("1234@gmail.com", "password");
        System.out.println(user1);
        User user2 = authenticationService.login("1234@gmail.com", "password");
        System.out.println(user2);
    }
}
