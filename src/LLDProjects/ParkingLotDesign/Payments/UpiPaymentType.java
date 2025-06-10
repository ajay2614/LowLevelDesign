package LLDProjects.ParkingLotDesign.Payments;

public class UpiPaymentType implements PaymentType{

    public void paymentTransaction(double amount) {
        System.out.println("Payment done via upi, amount : " + amount);
    }
}
