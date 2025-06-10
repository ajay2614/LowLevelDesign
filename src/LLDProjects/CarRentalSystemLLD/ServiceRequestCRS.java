package LLDProjects.CarRentalSystemLLD;

public class ServiceRequestCRS {
    BillCRS billCRS;
    VehicleCRS vehicleCRS;
    int ServiceRequestId;
    StoreCRS storeCRS;
    boolean reqOpen;

    public ServiceRequestCRS(StoreCRS storeCRS, int ServiceRequestId, VehicleCRS vehicleCRS, int billId, int totalDays) {
        this.storeCRS = storeCRS;
        this.ServiceRequestId = ServiceRequestId;
        this.billCRS = new BillCRS(billId, vehicleCRS.getVehiclePricePerDay(), totalDays);
        this.vehicleCRS = vehicleCRS;
        this.reqOpen = true;
    }

}
