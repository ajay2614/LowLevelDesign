package ParkingLotDesign.Payments;

import ParkingLotDesign.Vehicles.Vehicle;

import java.time.Instant;

public class PaymentProcessor {
    public void processPayment(Vehicle vehicle, String paymentType) {
        PaymentTypeFactory paymentTypeFactory = new PaymentTypeFactory();
        PaymentType paymentTypeObj = paymentTypeFactory.getPaymentType(paymentType);

        AmountFactory amountFactory = new AmountFactory();
        double amount = amountFactory.getAmount(vehicle.getVehicleType(), vehicle.getEnteranceTime());

        paymentTypeObj.paymentTransaction(amount);

    }
}
