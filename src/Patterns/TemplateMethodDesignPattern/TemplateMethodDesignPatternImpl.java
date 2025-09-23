package Patterns.TemplateMethodDesignPattern;

/**
 * Abstract base class representing a generic Payment.
 * Implements Template Method pattern:
 * - final method defines workflow
 * - abstract methods allow dynamic behavior
 */
abstract class Payment {

    /**
     * Template method defining the payment workflow.
     * Final to prevent overriding.
     * Calls abstract methods, which will execute overridden versions in child classes.
     */
    public final void makePayment() {
        String userSelected = payToUser();   // dynamic step (child’s override)
        int amt = totalAmount();             // dynamic step (child’s override)
        amt += platformCharges();            // dynamic step (child’s override)
        amt += taxCharges();                 // dynamic step (child’s override)

        System.out.println("Successfully paid amt = " + amt + " to user = " + userSelected);
    }

    // Abstract methods to be implemented by subclasses
    public abstract String payToUser();
    public abstract int totalAmount();
    public abstract int platformCharges();
    public abstract int taxCharges();
}

/**
 * Payment to friends or family (no charges)
 */
class PaymentToFriendOrFamily extends Payment {
    private String user;
    private int amount;

    public PaymentToFriendOrFamily(String user, int amount) {
        this.user = user;
        this.amount = amount;
    }

    @Override
    public String payToUser() { return user; }

    @Override
    public int totalAmount() { return amount; }

    @Override
    public int platformCharges() { return 0; }

    @Override
    public int taxCharges() { return 0; }
}

/**
 * Payment to merchants (1% platform + 5% tax)
 */
class PaymentToMerchant extends Payment {
    private String merchant;
    private int itemAmount;

    public PaymentToMerchant(String merchant, int itemAmount) {
        this.merchant = merchant;
        this.itemAmount = itemAmount;
    }

    @Override
    public String payToUser() { return merchant; }

    @Override
    public int totalAmount() { return itemAmount; }

    @Override
    public int platformCharges() { return (itemAmount * 1) / 100; }

    @Override
    public int taxCharges() { return (itemAmount * 5) / 100; }
}

/**
 * Main class to test the Template Method implementation
 */
public class TemplateMethodDesignPatternImpl {
    public static void main(String[] args) {
        Payment friendPayment = new PaymentToFriendOrFamily("Ajay", 1000);
        friendPayment.makePayment();

        Payment merchantPayment = new PaymentToMerchant("Amazon", 2000);
        merchantPayment.makePayment();
    }
}
