package ParkingLotDesign.Vehicles;

import java.time.Instant;

public class Bike implements Vehicle{
    String license;
    Instant enteranceTime;
    Bike(String license) {
        this.license = license;
    }

    public String getVehicleType() {
        return "Bike";
    }
    public String getVehicleLicense() {
        return license;
    }

    @Override
    public void setEnteranceTime(Instant enteranceTime) {
        this.enteranceTime = enteranceTime;
    }

    public Instant getEnteranceTime() {
        return enteranceTime;
    }

}
