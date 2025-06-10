package LLDProjects.MovieBookingApp;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ShowTime {
    Integer showtimeId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String movieName;
    public HashMap<Integer, Availability> seats = new HashMap<>();


    public ShowTime(Integer showtimeId, LocalDateTime startTime, LocalDateTime endTime, String movieName, int totalSeats) {
        this.showtimeId = showtimeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movieName = movieName;

        for(int i = 1 ;i <= totalSeats; i++) {
            seats.put(i, Availability.Empty);
        }
    }
}
