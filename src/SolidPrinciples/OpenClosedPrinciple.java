package SolidPrinciples;

public class OpenClosedPrinciple {
}

/**
 * Open/Closed Principle (OCP)
 * Definition: A class should be open for extension but closed for modification.
 * Why? Instead of modifying existing code (which can introduce bugs),
 * extend it using inheritance, interfaces, or composition.
 */

class PaymentProcessor {
    void processPayment(String type) {
        if (type.equals("CreditCard")) { /* Process credit card */ }
        else if (type.equals("PayPal")) { /* Process PayPal */ }
    }
}

interface Payment {
    void process();
}
class CreditCardPayment implements Payment {
    public void process() { /* Process credit card */ }
}
class PayPalPayment implements Payment {
    public void process() { /* Process PayPal */ }
}
class ProcessingPayment {
    void processPayment(Payment payment) { payment.process(); }
}
