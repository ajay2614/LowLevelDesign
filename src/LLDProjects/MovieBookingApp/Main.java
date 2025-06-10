package LLDProjects.MovieBookingApp;

// 🟡 Minor Gaps to Be Aware Of (If the Interviewer Pushes Further)

// • User Booking Flow:
//   How to extend for user bookings? -> Add User class, Booking class, and map bookings to users.

// • Scalability:
//   What if there are 1000 theatres in 200 cities? -> Use indexing, database storage, and searchable APIs.

// • Thread Safety:
//   What if two users try to book the same seat at the same time? -> Use synchronized blocks or concurrency-safe data structures.

// • Testability:
//   How to write unit tests? -> Extract business logic into service classes for better testability.

// • Decoupling:
//   How to separate controller and business


import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        CityController cityController = new CityController();

        // 1. Add a City
        cityController.addCity(1, "Bangalore");

        // 2. Get City and Add Theatre
        City bangalore = cityController.cities.get(0);
        bangalore.createTheatre(101, "PVR Orion");

        // 3. Add Screens to Theatre
        Theatre pvrOrion = bangalore.getTheatre(101);
        pvrOrion.addScreen(1, 10); // 10 seats in screen 1

        // 4. Add Movie
        Movie movie = new Movie();
        movie.movieId = 1001;
        movie.movieName = "Bad Movie";

        // 5. Add Movie Show to Hall
        LocalDateTime now = LocalDateTime.now().plusHours(1); // show 1 hour from now
        LocalDateTime end = now.plusHours(2); // 2-hour movie

        pvrOrion.addMovieInHall(1, movie, 201, now, end);

        // 6. Book Seats
        ScreeningHall hall = pvrOrion.screeningHalls.get(0);
        boolean bookingResult = hall.bookSeat(201, Arrays.asList(1, 2, 3));
        System.out.println("Booking successful? " + bookingResult);

        // 7. Print Data
        cityController.printAllCities();
        cityController.printAllTheatres(1);
        cityController.printAllMoviesInCity(1);
        cityController.printMovieTimingsInTheatre(1, 101, 1001);
    }

}
