package LLDProjects.ParkingLotDesign.Vehicles;

import java.time.Instant;

public interface Vehicle {

    public String getVehicleType();
    public String getVehicleLicense();
    public void setEnteranceTime(Instant enteranceTime);
    public Instant getEnteranceTime();
}
