package LLDProjects.ParkingLotDesign.ParkingLot;

import LLDProjects.ParkingLotDesign.Vehicles.Vehicle;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    List<ParkingFloor> parkingFloorList;
    int floorNum;

    public ParkingLot() {
        this.parkingFloorList = new ArrayList<>();
        this.floorNum = 0;
    }
    public void addFloor(int totalCars, int totalBikes) {
        ParkingFloor parkingFloor = new ParkingFloor(floorNum++, totalCars, totalBikes);
        parkingFloorList.add(parkingFloor);
    }

    public void bulkAddFloor(int totalFloorAdd, int totalCarPerFloor, int totalBikePerFloor) {
        for(int i=0;i<totalFloorAdd;i++) {
            ParkingFloor parkingFloor = new ParkingFloor(floorNum++, totalCarPerFloor, totalBikePerFloor);
            parkingFloorList.add(parkingFloor);
        }
    }

    public void parkVehicle(Vehicle vehicle) throws Exception {
        //Find The Parking

        boolean vehicleParked = false;
        for(ParkingFloor parkingFloor : parkingFloorList) {
            if(vehicleParked)
                continue;
            vehicleParked = parkingFloor.park(vehicle.getVehicleType(), vehicle.getVehicleLicense());
        }

        if(!vehicleParked)
            throw new Exception("No Parking Spot Available");

        vehicle.setEnteranceTime(Instant.now());
    }

    public void exitVehicle(Vehicle vehicle) throws Exception {

        boolean vehicleExit = false;

        for(ParkingFloor parkingFloor : parkingFloorList) {
            if(vehicleExit)
                continue;
            vehicleExit = parkingFloor.exit(vehicle.getVehicleType(), vehicle.getVehicleLicense());
        }

        if(!vehicleExit)
            throw new Exception("Incorrect License Entered");
    }

}
