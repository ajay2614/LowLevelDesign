package LLDProjects.CarRentalSystemLLD;

import java.util.Scanner;

public class CRSMain {

    public static void main(String[] args) {
        AppController appController = new AppController();

        StoreCRS storeCRS = new StoreCRS(1, 1321, "ABC_CITY");
        StoreCRS storeCRS2 = new StoreCRS(2, 2222, "NT_CITY");


        VehicleCRS honda = new CarCRS(1, "Honda", 500);
        VehicleCRS tata = new CarCRS(2, "TATA", 450);
        VehicleCRS hyundai = new CarCRS(3, "Hyundai", 480);

        VehicleCRS bullet = new BikeCRS(4, "Bullet", 400 );


        storeCRS.addVehicle(honda);
        storeCRS.addVehicle(tata);
        storeCRS.addVehicle(hyundai);
        storeCRS.addVehicle(bullet);

        appController.addAStore(storeCRS);

        UserCRS userCRS= appController.createUser(1, "lcsn");

        Scanner sc = new Scanner(System.in);

        int pincode = sc.nextInt();

        StoreCRS store = appController.fetchStoreByPincode(pincode);

        int vehicleId = sc.nextInt();
        VehicleCRS selectedVehicle = appController.getVehicleById(vehicleId, store);

        int totalDays = sc.nextInt();
        userCRS.addRequest(appController.bookAVehicle(store, selectedVehicle, 1, 1, totalDays));

        String paymentType = sc.next();

        appController.payBill(userCRS.getServiceRequest(1), paymentType);


        appController.returnAVehicle(userCRS.getServiceRequest(1));







    }
}
