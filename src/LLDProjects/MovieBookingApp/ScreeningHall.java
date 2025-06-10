package LLDProjects.MovieBookingApp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScreeningHall {

    public Integer screenHallId;
    public List<ShowTime> showTimes = new ArrayList<>();
    int totalSeats;

    public ScreeningHall(int screenHallId, int totalSeats) {
        this.screenHallId = screenHallId;
        this.totalSeats = totalSeats;
    }

    public boolean addShow(Integer showTimeId, LocalDateTime startTime, LocalDateTime endTime, String movieName) {
        if(startTime.isBefore(LocalDateTime.now())) {
            System.out.println("Incorrect Time added, time cannot be in past");
            return false;
        }
        if(startTime.isAfter(endTime)) {
            System.out.println("Incorrect Time added, end time cannot be before start time");
            return false;
        }

        for(ShowTime showTime : showTimes) {
            LocalDateTime presentEndTime = showTime.endTime;
            presentEndTime = presentEndTime.plusMinutes(30);

            if(startTime.isBefore(presentEndTime)) {
                System.out.println("Incorrect Time added, already a movie is running at the moment");
                return false;
            }
        }
        showTimes.add(new ShowTime(showTimeId, startTime, endTime, movieName, totalSeats));
        return true;
    }

    public boolean bookSeat(Integer showtimeId, List<Integer> seatsToBook) {
        for(ShowTime showTime : showTimes) {
            if(showTime.showtimeId.equals(showtimeId)) {
                for(Integer seatNo : seatsToBook) {
                    if(showTime.seats.containsKey(seatNo) && showTime.seats.get(seatNo).equals(Availability.Occupied)) {
                        System.out.println("Sorry, the entered seat : " + seatNo + " is already taken");
                        return false;
                    }
                }
                for(Integer seatNo : seatsToBook) {
                    showTime.seats.put(seatNo, Availability.Occupied);
                }
                return true;
            }
        }
        return false;
    }

}
