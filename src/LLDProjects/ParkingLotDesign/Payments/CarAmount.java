package LLDProjects.ParkingLotDesign.Payments;

import java.time.Instant;
import java.time.Duration;

public class CarAmount implements Amount{
    double amount = 20.0;
    Instant enteranceTime;

    CarAmount(Instant enteranceTime) {
        this.enteranceTime = enteranceTime;
    }


    @Override
    public double getTotalAmount() {

        Instant exitTime = Instant.now();
        long minutes = Duration.between(enteranceTime, exitTime).toMinutes();
        long roundedHours = (long) Math.ceil(minutes / 60.0);

        return amount * roundedHours;
    }
}
