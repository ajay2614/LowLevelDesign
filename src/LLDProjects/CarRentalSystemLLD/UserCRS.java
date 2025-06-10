package LLDProjects.CarRentalSystemLLD;

import java.util.ArrayList;
import java.util.List;

public class UserCRS {
    int userId;
    String license;
    String paymentType;
    List<ServiceRequestCRS> serviceRequests = new ArrayList<>();

    public UserCRS(int userId, String license) {
        this.userId = userId;
        this.license = license;
    }

    public void addRequest(ServiceRequestCRS serviceRequestCRS) {
        this.serviceRequests.add(serviceRequestCRS);
    }

    public ServiceRequestCRS getServiceRequest(int requestId) {
        for(ServiceRequestCRS serviceRequestCRS : serviceRequests) {
            if(serviceRequestCRS.ServiceRequestId == requestId) {
                return serviceRequestCRS;
            }
        }
        return null;
    }

}
