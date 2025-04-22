package ParkingLotDesign;

import ParkingLotDesign.ParkingLot.ParkingLot;
import ParkingLotDesign.Payments.PaymentProcessor;
import ParkingLotDesign.Vehicles.Vehicle;
import ParkingLotDesign.Vehicles.VehicleFactory;

public class ParkingLotMain {

    public static void main(String[] args) throws Exception {
        Vehicle car1 = new VehicleFactory().getVehicle("Car", "CARA123");
        Vehicle car2 = new VehicleFactory().getVehicle("Car", "CARA456");

        Vehicle bike1 = new VehicleFactory().getVehicle("Bike", "BKA123");
        Vehicle bike2 = new VehicleFactory().getVehicle("Bike", "BKA456");
        Vehicle bike3 = new VehicleFactory().getVehicle("Bike", "BKA789");

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.addFloor(2, 2);

        parkingLot.parkVehicle(car1);
        parkingLot.parkVehicle(car2);

        parkingLot.parkVehicle(bike1);
        parkingLot.parkVehicle(bike2);



        parkingLot.exitVehicle(car1);

        PaymentProcessor paymentProcessor = new PaymentProcessor();
        paymentProcessor.processPayment(car1, "Cash");

        Vehicle car3 = new VehicleFactory().getVehicle("Car", "CARA125");
        parkingLot.parkVehicle(car3);
    }
}
