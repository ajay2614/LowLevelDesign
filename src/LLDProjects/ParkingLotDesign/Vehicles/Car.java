package LLDProjects.ParkingLotDesign.Vehicles;

import java.time.Instant;

public class Car implements Vehicle{
    String license;
    String type;
    Instant enteranceTime;

    Car(String license) {
        this.license = license;
        this.type = "Car";
    }

    public String getVehicleType() {
        return "Car";
    }

    public String getVehicleLicense() {
        return license;
    }

    @Override
    public void setEnteranceTime(Instant enteranceTime) {
        this.enteranceTime = enteranceTime;
    }

    @Override
    public Instant getEnteranceTime() {
        return enteranceTime;
    }
}
