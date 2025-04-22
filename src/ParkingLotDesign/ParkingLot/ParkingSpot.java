package ParkingLotDesign.ParkingLot;

import ParkingLotDesign.Vehicles.Vehicle;

public abstract class ParkingSpot implements ParkingSpotInterface{
    boolean vacant;
    int floorId;
    String license;

    ParkingSpot(int floorId) {
        this.vacant = true;
        this.floorId = floorId;
    }

    public boolean isVacant() {
        return vacant;
    }

    public void parkVehicle(String license) {
        this.vacant = false;
        this.license = license;
    }

    public String getParkedVehicleLicense() {
        return this.license;
    }

    public void exitVehicle() {
        this.vacant = true;
        this.license = null;
    }


}
