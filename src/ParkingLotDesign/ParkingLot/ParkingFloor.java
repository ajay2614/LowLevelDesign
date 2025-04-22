package ParkingLotDesign.ParkingLot;
import java.util.ArrayList;
import java.util.List;
public class ParkingFloor {

    int parkingFloorNumber;
    List<ParkingSpotInterface> carParkingSpots;
    List<ParkingSpotInterface> bikeParkingSpots;
    int carParkingSpotNum = 0;
    int bikeParkingSpotNum = 0;
    ParkingFloor(int parkingFloorNumber, int carParkingSpotNum, int bikeParkingSpotNum) {
        this.parkingFloorNumber = parkingFloorNumber;
        this.carParkingSpotNum = carParkingSpotNum;
        this.bikeParkingSpotNum = bikeParkingSpotNum;

        fillSpots(parkingFloorNumber, carParkingSpotNum, "Car", new ArrayList<>());
        fillSpots(parkingFloorNumber, carParkingSpotNum, "Bike", new ArrayList<>());
    }

    private void fillSpots(int parkingFloorNumber, int totalVehicles, String vehicleType, List<ParkingSpotInterface> parkingSpots) {
        for(int i=0;i<totalVehicles;i++) {
            ParkingSpotFactory parkingSpotFactory = new ParkingSpotFactory();
            ParkingSpotInterface parkingSpotInterface = parkingSpotFactory.getParkingSpot(vehicleType, parkingFloorNumber);
            parkingSpots.add(parkingSpotInterface);
        }

        if(vehicleType.equalsIgnoreCase("Car"))
            this.carParkingSpots = parkingSpots;
        if(vehicleType.equalsIgnoreCase("Bike"))
            this.bikeParkingSpots = parkingSpots;
    }

    public boolean park(String vehicleType, String license) {
        if(vehicleType.equalsIgnoreCase("Car"))
            return parkCar(license);
        if(vehicleType.equalsIgnoreCase("Bike"))
            return parkBike(license);

        return false;
    }

    public boolean parkCar(String license) {
        if(carParkingSpots.isEmpty())
            return false;
        for(ParkingSpotInterface carParkingSpot : carParkingSpots) {
            if(carParkingSpot.isVacant()) {
                carParkingSpot.parkVehicle(license);
                return true;
            }
        }
        return false;
    }

    public boolean parkBike(String license) {
        if(bikeParkingSpots.isEmpty())
            return false;
        for(ParkingSpotInterface bikeParkingSpot : bikeParkingSpots) {
            if(bikeParkingSpot.isVacant()) {
                bikeParkingSpot.parkVehicle(license);
                return true;
            }
        }
        return false;
    }

    public boolean exit(String vehicleType, String license) {
        if(vehicleType.equalsIgnoreCase("Car"))
            return exitCar(license);
        if(vehicleType.equalsIgnoreCase("Bike"))
            return exitBike(license);

        return false;
    }

    public boolean exitCar(String license) {
        if(carParkingSpots.isEmpty())
            return true;
        for (ParkingSpotInterface carParkingSpot: carParkingSpots) {
            if(carParkingSpot.getParkedVehicleLicense().equalsIgnoreCase(license)) {
                carParkingSpot.exitVehicle();
            }
            return true;
        }
        return false;
    }

    public boolean exitBike(String license) {
        if(bikeParkingSpots.isEmpty())
            return true;
        for (ParkingSpotInterface bikeParkingSpot: bikeParkingSpots) {
            if(bikeParkingSpot.getParkedVehicleLicense().equalsIgnoreCase(license)) {
                bikeParkingSpot.exitVehicle();
            }
            return true;
        }
        return false;
    }
}
