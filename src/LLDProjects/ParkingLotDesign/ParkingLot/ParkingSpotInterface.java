package LLDProjects.ParkingLotDesign.ParkingLot;

public interface ParkingSpotInterface {
    public boolean isVacant();
    public void parkVehicle(String license);
    public String getParkedVehicleLicense();
    public void exitVehicle();
}
