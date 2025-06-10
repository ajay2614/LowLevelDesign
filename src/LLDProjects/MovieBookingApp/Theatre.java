package LLDProjects.MovieBookingApp;

import java.time.LocalDateTime;
import java.util.*;

public class Theatre {

    Integer theatreId;
    String theatreName;
    List<ScreeningHall> screeningHalls = new ArrayList<>();
    Set<String> movies = new HashSet<>();
    HashMap<Movie, List<LocalDateTime>> moviesTimeMap = new HashMap<>();

    public Theatre(Integer theatreId, String theatreName) {
        this.theatreId = theatreId;
        this.theatreName = theatreName;
    }

    public void addScreen(Integer screenId, Integer totalSeats) {
        ScreeningHall screeningHall = new ScreeningHall(screenId, totalSeats);
        screeningHalls.add(screeningHall);
    }

    public void addMovieInHall(Integer screenId, Movie movie, Integer showTimeId, LocalDateTime startTime, LocalDateTime endTime) {
        boolean movieAdded;
        for(ScreeningHall screeningHall : screeningHalls) {
            if(screeningHall.screenHallId.equals(screenId)) {
                movieAdded = screeningHall.addShow(showTimeId, startTime, endTime, movie.movieName);
                if(movieAdded) {
                    System.out.println("Movie + " + movie.movieName + " added successfully in hall number " + screenId);
                    movies.add(movie.movieName);
                    if(moviesTimeMap.containsKey(movie)) {
                        List<LocalDateTime> movieShowTimes = moviesTimeMap.get(movie);
                        movieShowTimes.add(startTime);
                        moviesTimeMap.put(movie, movieShowTimes);
                    }
                    else {
                        List<LocalDateTime> movieShowTimes = new ArrayList<>();
                        movieShowTimes.add(startTime);
                        moviesTimeMap.put(movie, movieShowTimes);
                    }
                }
                else {
                    System.out.println("Unable to add Movie + " + movie.movieName + " in hall number " + screenId);
                }
                return;
            }
        }
        System.out.println("Unable to fetch hall number " + screenId);
    }
}
