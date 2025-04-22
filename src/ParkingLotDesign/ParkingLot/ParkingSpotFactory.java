package ParkingLotDesign.ParkingLot;

import ParkingLotDesign.Vehicles.Vehicle;

public class ParkingSpotFactory {

    public  ParkingSpotInterface getParkingSpot(String type, int id) {

        if(type.equalsIgnoreCase("Car")) {
            return new CarParkingSpot(id);
        }
        if(type.equalsIgnoreCase("Bike")) {
            return new BikeParkingSpot(id);
        }

        throw new RuntimeException("Given Vehicle Type not suited for parking");
    }
}
