package LLDProjects.CarRentalSystemLLD;

import java.util.ArrayList;
import java.util.List;

public class StoreCRS {
    int storeId;
    int pinCode;
    String district;
    List<VehicleCRS> vehicleCRSList = new ArrayList<>();

    public StoreCRS(int storeId, int pinCode, String district) {
        this.storeId = storeId;
        this.pinCode = pinCode;
        this.district = district;
    }

    public void addVehicle(VehicleCRS vehicleCRS) {
        vehicleCRSList.add(vehicleCRS);
    }

    public void deleteVehicle(int vehicleId, String type) {
        vehicleCRSList.removeIf(vehicleCRS -> vehicleCRS.getType().equals(type) && vehicleCRS.getId() == vehicleId);
    }

    public void showAllAvailableVehicles() {
        vehicleCRSList.stream()
                .filter(VehicleCRS::status)
                .forEach(vehicleCRS -> System.out.println(
                        "Vehicle Type = " + vehicleCRS.getType() + " Vehicle Name = " + vehicleCRS.getName()));
    }


    public VehicleCRS getVehicle(int vehicleId) {
        for(VehicleCRS vehicleCRS : vehicleCRSList) {
            if(vehicleCRS.getId() == vehicleId && vehicleCRS.status()) {
                return vehicleCRS;
            }
        }
        return null;
    }

    public void bookVehicle(int vehicleId) {
        for(VehicleCRS vehicleCRS : vehicleCRSList) {
            if(vehicleCRS.getId() == vehicleId) {
                vehicleCRS.setStatus(false);
            }
        }
    }

    public void returnVehicle(int vehicleId) {
        for(VehicleCRS vehicleCRS : vehicleCRSList) {
            if(vehicleCRS.getId() == vehicleId) {
                vehicleCRS.setStatus(true);
            }
        }
    }
}
