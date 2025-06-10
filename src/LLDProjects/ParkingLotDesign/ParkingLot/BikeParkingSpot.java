package LLDProjects.ParkingLotDesign.ParkingLot;

public class BikeParkingSpot extends ParkingSpot{
    public String getSpotType() {
        return "Car";
    }

    BikeParkingSpot(int floorId) {
        super(floorId);
    }
}
