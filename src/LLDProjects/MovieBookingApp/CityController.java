package LLDProjects.MovieBookingApp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CityController {

    List<City> cities = new ArrayList<>();


    public void addCity(Integer id, String cityName) {
        City city = new City(id, cityName);
        cities.add(city);
    }

    public void printAllCities() {
        System.out.println("Present Cities : ");
        for(City city : cities) {
            System.out.println(city.cityName);
        }
    }

    public void printAllTheatres(Integer cityId) {
        for(City city : cities) {
            if(city.cityId.equals(cityId)) {
                System.out.println("Present theatres in city " + city.cityName + " :");
                for(Theatre theatre : city.theatres) {
                    System.out.println(theatre.theatreName);
                }
            }
        }
    }

    public void printAllMoviesInCity(Integer cityId) {
        for(City city : cities) {
            if(city.cityId.equals(cityId)) {
                System.out.println("Present movies playing in city " + city.cityName + " :");
                for(Theatre theatre : city.theatres) {
                    System.out.println("Theatre " + theatre.theatreName);
                    for(String movie : theatre.movies) {
                        System.out.println(movie);
                    }
                }
            }
        }
    }

    public void printMovieTimingsInTheatre(Integer cityId, Integer theatreId, Integer movieId) {

        for(City city : cities) {
            if(city.cityId.equals(cityId)) {
                for(Theatre theatre : city.theatres) {
                    if(theatre.theatreId.equals(theatreId)) {
                        for (Map.Entry<Movie, List<LocalDateTime>> entry : theatre.moviesTimeMap.entrySet()) {
                            if(entry.getKey().movieId.equals(movieId)) {
                                System.out.println(entry.getKey().movieName + " playing at => ");
                                for(LocalDateTime movieTime : entry.getValue()) {
                                    System.out.println(movieTime.toString());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
