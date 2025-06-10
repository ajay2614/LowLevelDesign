package LLDProjects.ParkingLotDesign.Payments;

public class PaymentTypeFactory {

    PaymentType getPaymentType(String paymentType) {

        if(paymentType.equalsIgnoreCase("Cash")) {
            return new CashPaymentType();
        }
        if(paymentType.equalsIgnoreCase("UPI")) {
            return new UpiPaymentType();
        }

        throw new RuntimeException("Incorrect Payment Type");
    }
}
