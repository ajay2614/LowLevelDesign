package LLDProjects.ParkingLotDesign.Payments;

public class CashPaymentType implements PaymentType{

    public void paymentTransaction(double amount) {
        System.out.println("Payment done via cash, amount : " + amount);
    }

}
