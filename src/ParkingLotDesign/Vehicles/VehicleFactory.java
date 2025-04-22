package ParkingLotDesign.Vehicles;

import java.time.Instant;

public class VehicleFactory {

    public Vehicle getVehicle(String type, String license) {
        Vehicle vehicle = null;
        if(type.equalsIgnoreCase("Car")) {
            vehicle = new Car(license);
        }
        else if(type.equalsIgnoreCase("Bike")) {
            vehicle = new Bike(license);
        }

        if(vehicle == null)
            throw new RuntimeException("Vehicle type not accessible");

        return vehicle;
    }



}
