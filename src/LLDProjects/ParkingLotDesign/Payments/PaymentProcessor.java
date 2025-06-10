package LLDProjects.ParkingLotDesign.Payments;

import LLDProjects.ParkingLotDesign.Vehicles.Vehicle;

public class PaymentProcessor {
    public void processPayment(Vehicle vehicle, String paymentType) {
        PaymentTypeFactory paymentTypeFactory = new PaymentTypeFactory();
        PaymentType paymentTypeObj = paymentTypeFactory.getPaymentType(paymentType);

        AmountFactory amountFactory = new AmountFactory();
        double amount = amountFactory.getAmount(vehicle.getVehicleType(), vehicle.getEnteranceTime());

        paymentTypeObj.paymentTransaction(amount);

    }
}
