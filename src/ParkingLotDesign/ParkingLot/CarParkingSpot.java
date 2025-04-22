package ParkingLotDesign.ParkingLot;

public class CarParkingSpot extends ParkingSpot{

    public String getSpotType() {
        return "Car";
    }

    CarParkingSpot(int floorId) {
        super(floorId);
    }

}
