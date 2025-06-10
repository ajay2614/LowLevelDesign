package LLDProjects.MovieBookingApp;

import java.util.ArrayList;
import java.util.List;

public class City {
    Integer cityId;
    List<Theatre> theatres = new ArrayList<>();
    String cityName;
    public City(Integer cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public void createTheatre(int theatreId, String theatreName) {
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatres.add(theatre);
    }

    public Theatre getTheatre(int theatreId) {
        for(Theatre theatre : theatres) {
            if(theatre.theatreId.equals(theatreId))
                return theatre;
        }

        return null;
    }

    public void printAllMoviesShowing() {
        for(Theatre theatre : theatres) {
            System.out.println("Movies Showing at " + theatre.theatreName + " :" );
            theatre.movies.forEach(System.out::println);
        }
    }
}
