package ParkingLotDesign.Payments;

import java.time.Instant;

public class AmountFactory {

    public double getAmount(String vehicleType, Instant enteranceTime) {
        if(vehicleType.equalsIgnoreCase("Bike"))
            return new BikeAmount(enteranceTime).getTotalAmount();
        if(vehicleType.equalsIgnoreCase("Car"))
            return new CarAmount(enteranceTime).getTotalAmount();

        throw new RuntimeException("Vehicle Type not correct for collecting amount");
    }
}
