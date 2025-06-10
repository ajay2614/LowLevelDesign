package LLDProjects.CarRentalSystemLLD;

import java.util.ArrayList;
import java.util.List;

public class AppController {
    List<StoreCRS> storeCRSList = new ArrayList<>();

    public void addAStore(StoreCRS storeCRS) {
        this.storeCRSList.add(storeCRS);
    }

    public StoreCRS fetchStoreByPincode(int pincode) {
        for(StoreCRS storeCRS : storeCRSList) {
            if(storeCRS.pinCode == pincode) {
                return storeCRS;
            }
        }
        return null;
    }

    public VehicleCRS getVehicleById(int id, StoreCRS storeCRS) {
        return storeCRS.getVehicle(id);
    }

    public ServiceRequestCRS bookAVehicle(StoreCRS storeCRS, VehicleCRS vehicleCRS, int srId, int billId, int totalDays) {
        ServiceRequestCRS serviceRequestCRS = new ServiceRequestCRS(storeCRS, srId, vehicleCRS, billId, totalDays);
        serviceRequestCRS.storeCRS.bookVehicle(serviceRequestCRS.vehicleCRS.getId());

        return serviceRequestCRS;
    }

    public void returnAVehicle(ServiceRequestCRS serviceRequestCRS) {
        serviceRequestCRS.storeCRS.returnVehicle(serviceRequestCRS.vehicleCRS.getId());
    }

    public void payBill(ServiceRequestCRS serviceRequestCRS, String payType) {
        serviceRequestCRS.billCRS.payBill(payType);
    }

    public UserCRS createUser(int userId, String license) {
        return new UserCRS(userId, license);
    }


}
